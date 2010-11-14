package com.xbean.converters.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.xbean.annotations.Convertible;
import com.xbean.annotations.Ignore;
import com.xbean.converters.BeanConverter;
import com.xbean.converters.PropertyConverter;
import com.xbean.exceptions.CannotConvertException;
import com.xbean.util.XBeanUtils;

/**
 * AnnotationBeanConverter - One way of implementing BeanConverter. This
 * converter performs conversion based on annotation's associated with each
 * property .
 * 
 * Note: If you want to do conversion in any other way, you can create your own
 * converter by implementing BeanConverter.
 * 
 * @author Aravind (aravinds86@gmail.com), Pradeep (mitecepradeep@gmail.com).
 * 
 */
public class AnnotationBeanConverter implements BeanConverter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.xbean.converters.BeanConverter#convertToList(java.util.List,
	 * java.lang.Class)
	 */
	public <S, D> List<D> convertToList(List<S> pSourceList, Class<D> pDestination)
			throws Exception {
		if (pDestination == null) {
			throw new NullPointerException("Parameter pDestination is null");
		}

		List<D> destList = new ArrayList<D>(pSourceList.size());
		if (pSourceList != null) {
			for (S source : pSourceList) {
				destList.add(convert(pDestination, new Object[] { source }));
			}
		}
		return destList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.xbean.converters.BeanConverter#convertToSet(java.util.Set,
	 * java.lang.Class)
	 */
	public <S, D> Set<D> convertToSet(Set<S> pSourceSet, Class<D> pDestination) throws Exception {
		if (pDestination == null) {
			throw new NullPointerException("Parameter pDestination is null");
		}

		Set<D> destSet = new HashSet<D>(pSourceSet.size());
		if (pSourceSet != null) {
			for (S source : pSourceSet) {
				destSet.add(convert(pDestination, new Object[] { source }));
			}
		}
		return destSet;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.xbean.converters.BeanConverter#convert(java.lang.Class, S[])
	 */
	public <S, D> D convert(Class<D> pTargetClass, S... pSourceInstances)
			throws CannotConvertException {
		D targetInstance;
		try {
			targetInstance = pTargetClass.newInstance();
		} catch (InstantiationException e) {
			throw new CannotConvertException(e);
		} catch (IllegalAccessException e) {
			throw new CannotConvertException(e);
		}
		convertByInstance(targetInstance, pSourceInstances);

		return targetInstance;
	}

	public <S, D> void convertByInstance(D targetInstance, S... pSourceInstances)
			throws CannotConvertException {
		convertByInstanceConverter(targetInstance,
				new HashSet<PropertyConverter<Object, Object>>(1), pSourceInstances);
	}

	public <S, D, P extends PropertyConverter<?, ?>> void convertByInstanceConverter(
			D pTargetInstance, Set<P> pConverterInstanceSet, S... pSourceInstances)
			throws CannotConvertException {

		if (pTargetInstance == null || pSourceInstances == null) {
			throw new NullPointerException(
					"One or all parameters(targetInstance,pSourceInstances) are null");
		}

		Map<String, P> convertorsMap = XBeanUtils.createPropertyConverterMap(pConverterInstanceSet);

		try {
			Class<? extends Object> targetClass = pTargetInstance.getClass();

			Map<String, String> fieldsMap = createTargetFieldsMap(targetClass);

			for (S pSourceInstance : pSourceInstances) {

				Class<? extends Object> sourceClass = pSourceInstance.getClass();

				Field[] sourceFields = sourceClass.getDeclaredFields();
				for (Field sourceField : sourceFields) {
					Object targetObject = null; // to return.
					Field targetField = null;

					if (fieldsMap.containsKey(sourceField.getName())) {
						targetField = targetClass.getDeclaredField(fieldsMap.get(sourceField
								.getName()));
						targetField.setAccessible(true);
						sourceField.setAccessible(true);
						Object sourceFieldObj = sourceField.get(pSourceInstance);

						String key = getConverterName(targetField);
						if (key != null) {
							PropertyConverter<Object, Object> pConverter = (PropertyConverter<Object, Object>) convertorsMap
									.get(key);
							targetObject = pConverter.convert(sourceFieldObj);

						} else if (sourceFieldObj instanceof List<?>) {
							if (!(targetField.get(pTargetInstance) instanceof List<?>)) {
								System.out.println("Destination Field=" + targetField.getName()
										+ " is not instance of List or it may be null");
							}
							try {
								ParameterizedType dparamType = (ParameterizedType) targetField
										.getGenericType();
								targetObject = convertToList((List<?>) sourceFieldObj,
										(Class<?>) dparamType.getActualTypeArguments()[0]);
							} catch (Exception e) {
								e.printStackTrace();
							}
						} else if (sourceField.get(pSourceInstance) instanceof Set<?>) {
							if (!(targetField.get(pTargetInstance) instanceof Set<?>)) {
								System.out.println("Destination Field=" + targetField.getName()
										+ " is not instance of Set or it may be null");
							}
							try {
								ParameterizedType dparamType = (ParameterizedType) targetField
										.getGenericType();
								targetObject = convertToSet((Set<?>) sourceFieldObj,
										(Class<?>) dparamType.getActualTypeArguments()[0]);
							} catch (Exception e) {
								e.printStackTrace();
							}
						} else {
							targetObject = sourceFieldObj;
						}

						targetField.set(pTargetInstance, targetObject);
					}
				}
			}
		} catch (Exception e) {
			throw new CannotConvertException(e);
		}
	}

	private Map<String, String> createTargetFieldsMap(Class<? extends Object> pTargetClass) {
		Field[] targetFields = pTargetClass.getDeclaredFields();

		HashMap<String, String> fieldsMap = new HashMap<String, String>(targetFields.length);

		for (Field field : targetFields) {
			if (field.getAnnotation(Ignore.class) != null) {
				continue;
			}
			Convertible targetAnnotation = field.getAnnotation(Convertible.class);
			String sourceFieldName = null;
			if (targetAnnotation != null && !XBeanUtils.isEmptyString(targetAnnotation.value())) {
				sourceFieldName = targetAnnotation.value();
			} else {
				sourceFieldName = field.getName();
			}
			fieldsMap.put(sourceFieldName, field.getName());
		}
		return fieldsMap;
	}

	private String getConverterName(Field pTargetField) {
		Convertible targetAnnotation = pTargetField.getAnnotation(Convertible.class);

		if (targetAnnotation != null && targetAnnotation.convertor() != null
				&& targetAnnotation.convertor() != DefaultConvertor.class) {
			return targetAnnotation.convertor().getName();
		}
		return null;
	}

}
