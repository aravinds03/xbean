package com.xbean.converters.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.xbean.annotations.Converter;
import com.xbean.annotations.Convertible;
import com.xbean.annotations.Ignore;
import com.xbean.converters.BeanConverter;
import com.xbean.converters.PropertyConverter;
import com.xbean.exceptions.CannotConvertException;

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
	@Override
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
	@Override
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

		if (targetInstance == null || pSourceInstances == null) {
			throw new NullPointerException(
					"One or all parameters(targetInstance,pSourceInstances) are null");
		}
		try {
			Class<? extends Object> pTargetClass = targetInstance.getClass();

			Field[] targetFields = pTargetClass.getDeclaredFields();
			Map<String, String> fieldsMap = new HashMap<String, String>(targetFields.length);
			for (Field field : targetFields) {
				if (field.getAnnotation(Ignore.class) != null) {
					continue;
				}
				Convertible sourceAnnotation = field.getAnnotation(Convertible.class);

				if (sourceAnnotation != null && sourceAnnotation.value() != null
						&& !sourceAnnotation.value().equals("")) {
					fieldsMap.put(sourceAnnotation.value(), field.getName());
				} else {
					fieldsMap.put(field.getName(), field.getName());
				}
			}

			for (S pSourceInstance : pSourceInstances) {

				Class<? extends Object> sourceClass = pSourceInstance.getClass();

				Field[] sourceFields = sourceClass.getDeclaredFields();
				for (Field sourceField : sourceFields) {
					Object targetObject = null; // to return.
					Field targetField = null;

					if (fieldsMap.keySet().contains(sourceField.getName())) {
						targetField = pTargetClass.getDeclaredField(fieldsMap.get(sourceField
								.getName()));
						targetField.setAccessible(true);
						sourceField.setAccessible(true);
						Object sourceFieldObj = sourceField.get(pSourceInstance);

						Converter converter = targetField.getAnnotation(Converter.class);
						if (converter != null) {
							// This property must not be collection or map.
							Field converterField = pTargetClass.getDeclaredField(converter.value());
							converterField.setAccessible(true);
							PropertyConverter<Object, Object> pConverter = (PropertyConverter<Object, Object>) converterField
									.get(pTargetClass);
							targetObject = pConverter.convert(sourceFieldObj);
						}

						else if (sourceFieldObj instanceof List<?>) {
							if (!(targetField.get(targetInstance) instanceof List<?>)) {
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
							if (!(targetField.get(targetInstance) instanceof Set<?>)) {
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

						targetField.set(targetInstance, targetObject);
					}

				}

			}

		} catch (Exception e) {
			throw new CannotConvertException(e);
		}
	}

	/*
	 * private <S> Field getAnnotatedField(Class<S> pSourceClass, Convertible
	 * pPropertyAnn) { Field sourceField = null; try { sourceField =
	 * pSourceClass.getDeclaredField(pPropertyAnn.alias()); } catch (Exception
	 * e) { System.out.println("Destination field with annotation=" +
	 * pPropertyAnn.alias() + " doesn't present in source class " +
	 * pSourceClass); } return sourceField; }
	 * 
	 * private <S> Field getDefaultField(Class<S> pSourceClass, Field pField) {
	 * Field sourceField = null; try { sourceField =
	 * pSourceClass.getDeclaredField(pField.getName()); } catch (Exception e) {
	 * System.out.println("Field=" + pField.getName() +
	 * " is not present in source class" + pSourceClass); }
	 * 
	 * return sourceField; }
	 */
}
