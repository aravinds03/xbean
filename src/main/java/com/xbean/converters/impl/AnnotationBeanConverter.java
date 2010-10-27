package com.xbean.converters.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.xbean.annotations.Converter;
import com.xbean.annotations.Ignore;
import com.xbean.annotations.Property;
import com.xbean.converters.BeanConverter;
import com.xbean.converters.PropertyConverter;

public class AnnotationBeanConverter implements BeanConverter {

	@Override
	public <S, D> List<D> convertToList(List<S> pSourceList, Class<D> pDestination)
			throws Exception {
		if (pDestination == null) {
			throw new NullPointerException("Parameter pDestination is null");
		}

		List<D> destList = new ArrayList<D>(pSourceList.size());
		if (pSourceList != null) {
			for (S source : pSourceList) {
				destList.add(convert(source, pDestination));
			}
		}
		return destList;
	}

	@Override
	public <S, D> Set<D> convertToSet(Set<S> pSourceSet, Class<D> pDestination) throws Exception {
		if (pDestination == null) {
			throw new NullPointerException("Parameter pDestination is null");
		}

		Set<D> destSet = new HashSet<D>(pSourceSet.size());
		if (pSourceSet != null) {
			for (S source : pSourceSet) {
				destSet.add(convert(source, pDestination));
			}
		}
		return destSet;
	}

	public <S, D> D convert(S pSource, Class<D> pDestination) throws Exception {
		D instance = pDestination.newInstance();

		Field[] destFields = pDestination.getDeclaredFields();

		for (Field destField : destFields) {
			Field sourceField = null;
			Object destinationObject = null; // to return.

			if (destField.getAnnotation(Ignore.class) != null) {
				continue;
			}
			Property annotation = destField.getAnnotation(Property.class);
			if (annotation != null) {
				sourceField = getAnnotatedField(pSource, annotation);
			} else {
				sourceField = getDefaultField(pSource, destField);
			}

			if (sourceField == null) {
				System.out.println("WARN:Source field is not available for destination field="
						+ destField.getName());
				continue;
			}
			destField.setAccessible(true);
			sourceField.setAccessible(true);
			Object sourceFieldObj = sourceField.get(pSource);

			Converter converter = destField.getAnnotation(Converter.class);
			if (converter != null) {
				// This property must not be collection or map.
				Field converterField = pDestination.getDeclaredField(converter.value());
				converterField.setAccessible(true);
				PropertyConverter pConverter = (PropertyConverter) converterField.get(pDestination);
				destinationObject = pConverter.convert(sourceFieldObj);
			}

			else if (sourceFieldObj instanceof List<?>) {
				if (!(destField.get(instance) instanceof List<?>)) {
					System.out.println("Destination Field=" + destField.getName()
							+ " is not instance of List or it may be null");
				}
				try {
					ParameterizedType dparamType = (ParameterizedType) destField.getGenericType();
					destinationObject = convertToList((List<?>) sourceFieldObj,
							(Class<?>) dparamType.getActualTypeArguments()[0]);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (sourceField.get(pSource) instanceof Set<?>) {
				if (!(destField.get(instance) instanceof Set<?>)) {
					System.out.println("Destination Field=" + destField.getName()
							+ " is not instance of Set or it may be null");
				}
				try {
					ParameterizedType dparamType = (ParameterizedType) destField.getGenericType();
					destinationObject = convertToSet((Set<?>) sourceFieldObj, (Class<?>) dparamType
							.getActualTypeArguments()[0]);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				destinationObject = sourceFieldObj;
			}

			destField.set(instance, destinationObject);

		}
		return instance;
	}

	private <S> Field getAnnotatedField(S pSource, Property pPropertyAnn) {
		Field sourceField = null;
		try {
			sourceField = pSource.getClass().getDeclaredField(pPropertyAnn.value());
		} catch (Exception e) {
			System.out.println("Destination field with annotation=" + pPropertyAnn.value()
					+ " doesn't present in source");
		}
		return sourceField;
	}

	private <S> Field getDefaultField(S pSource, Field pField) {
		Field sourceField = null;
		try {
			sourceField = pSource.getClass().getDeclaredField(pField.getName());
		} catch (Exception e) {
			System.out.println("Destination field=" + pField.getName()
					+ " doesn't present in source");
		}

		return sourceField;
	}

}
