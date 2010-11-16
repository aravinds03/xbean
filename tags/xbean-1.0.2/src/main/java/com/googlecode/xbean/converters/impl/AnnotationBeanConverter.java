package com.googlecode.xbean.converters.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.googlecode.xbean.annotations.Convertible;
import com.googlecode.xbean.converters.BeanConverter;
import com.googlecode.xbean.converters.PropertyConverter;
import com.googlecode.xbean.exceptions.CannotConvertException;
import com.googlecode.xbean.util.XBeanUtils;

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
 * @version $Revision: 1.0 $
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

	/**
	 * Method convertByInstance.
	 * 
	 * @param targetInstance
	 *            D
	 * @param pSourceInstances
	 *            S[]
	 * @throws CannotConvertException
	 * @see com.xbean.converters.BeanConverter#convertByInstance(D, S[])
	 */
	public <S, D> void convertByInstance(D targetInstance, S... pSourceInstances)
			throws CannotConvertException {
		convertByInstanceConverter(targetInstance,
				new HashSet<PropertyConverter<Object, Object>>(1), pSourceInstances);
	}

	/**
	 * Method convertByInstanceConverter.
	 * 
	 * @param pTargetInstance
	 *            D
	 * @param pConverterInstanceSet
	 *            Set
	 *            <P>
	 * @param pSourceInstances
	 *            S[]
	 * @throws CannotConvertException
	 * @see com.xbean.converters.BeanConverter#convertByInstanceConverter(D, Set
	 *      <P>
	 *      , S[])
	 */
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

			Map<String, String> fieldsMap = XBeanUtils.createTargetFieldsMap(targetClass);

			for (S pSourceInstance : pSourceInstances) {

				Field[] sourceFields = pSourceInstance.getClass().getDeclaredFields();

				for (Field sourceField : sourceFields) {
					Object targetObject = null; // to return.
					Field targetField = null;
					String key = null;
					if (fieldsMap.containsKey(sourceField.getName())) {
						targetField = targetClass.getDeclaredField(fieldsMap.get(sourceField
								.getName()));
						targetField.setAccessible(true);
						sourceField.setAccessible(true);
						Object sourceFieldObj = sourceField.get(pSourceInstance);

						// check auto property.
						if (isAutoAvailable(targetField)) {
							targetObject = convert(targetField.getType(), sourceFieldObj);
						}

						// check custom converter property.
						else if ((key = getConverterClassName(targetField)) != null) {
							PropertyConverter<Object, Object> pConverter = (PropertyConverter<Object, Object>) convertorsMap
									.get(key);
							targetObject = pConverter.convert(sourceFieldObj);

						} else if (sourceFieldObj instanceof List<?>) {
							try {
								ParameterizedType dparamType = (ParameterizedType) targetField
										.getGenericType();
								targetObject = convertToList((List<?>) sourceFieldObj,
										(Class<?>) dparamType.getActualTypeArguments()[0]);
							} catch (Exception e) {
								e.printStackTrace();
							}
						} else if (sourceField.get(pSourceInstance) instanceof Set<?>) {
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

	/**
	 * Method isAutoAvailable.
	 * 
	 * @param pTargetField
	 *            Field
	 * @return boolean -true if the target field has annotation Convertible
	 *         whose auto property is set to true.
	 */
	private boolean isAutoAvailable(Field pTargetField) {
		Convertible targetAnnotation = pTargetField.getAnnotation(Convertible.class);
		return targetAnnotation != null && targetAnnotation.auto();
	}

	/**
	 * Method getConverterClassName.
	 * 
	 * @param pTargetField
	 *            Field
	 * @return String - Returns property converter class name if available, else
	 *         null.
	 */
	private String getConverterClassName(Field pTargetField) {
		Convertible targetAnnotation = pTargetField.getAnnotation(Convertible.class);

		if (targetAnnotation != null && targetAnnotation.convertor() != null
				&& targetAnnotation.convertor() != DefaultConvertor.class) {
			return targetAnnotation.convertor().getName();
		}
		return null;
	}

}
