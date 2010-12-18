package com.googlecode.xbean.converters.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.googlecode.xbean.conversion.Conversion;
import com.googlecode.xbean.conversion.SourceDetails;
import com.googlecode.xbean.conversion.TargetDetails;
import com.googlecode.xbean.conversion.impl.AutoAnnotationConversion;
import com.googlecode.xbean.conversion.impl.CustomPropertyConversion;
import com.googlecode.xbean.conversion.impl.DefaultConversion;
import com.googlecode.xbean.conversion.impl.ListPropertyConversion;
import com.googlecode.xbean.conversion.impl.SetPropertyConversion;
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

	private List<Conversion> conversionList = new ArrayList<Conversion>();

	public AnnotationBeanConverter() {
		conversionList.add(new ListPropertyConversion(this));
		conversionList.add(new SetPropertyConversion(this));
		conversionList.add(new AutoAnnotationConversion(this));
		conversionList.add(new DefaultConversion());
	}

	/**
	 * This methods add extra conversions passed to the existing conversionList.
	 * All extra conversions will be added at the begining of the list. So ,
	 * extra conversion's will be executed first.
	 * 
	 * @param pConversionList
	 */
	public void setConversionList(List<Conversion> pConversionList) {
		conversionList.addAll(0, pConversionList);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.xbean.converters.BeanConverter#convertToList(java.util.List,
	 * java.lang.Class)
	 */
	public <S, D> List<D> convertToList(List<S> pSourceList, Class<D> pDestination)
			throws Exception {
		return new ListPropertyConversion(this).convertToList(pSourceList, pDestination);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.xbean.converters.BeanConverter#convertToSet(java.util.Set,
	 * java.lang.Class)
	 */
	public <S, D> Set<D> convertToSet(Set<S> pSourceSet, Class<D> pDestination) throws Exception {
		return new SetPropertyConversion(this).convertToSet(pSourceSet, pDestination);
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
		} catch (Exception e) {
			throw new CannotConvertException(e);
		}
		return convertByInstance(targetInstance, pSourceInstances);
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
	public <S, D> D convertByInstance(D targetInstance, S... pSourceInstances)
			throws CannotConvertException {
		return convertByInstanceConverter(targetInstance, XBeanUtils.emptySet, pSourceInstances);
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
	public <S, D, P extends PropertyConverter<?, ?>> D convertByInstanceConverter(
			D pTargetInstance, Set<P> pConverterInstanceSet, S... pSourceInstances)
			throws CannotConvertException {

		if (pTargetInstance == null || pSourceInstances == null) {
			throw new NullPointerException(
					"One or all parameters(targetInstance,pSourceInstances) are null");
		}

		Class<? extends Object> targetClass = pTargetInstance.getClass();

		if (XBeanUtils.isPrimitive(targetClass)) {
			S s = pSourceInstances[0];
			if (s.getClass().equals(targetClass)) {
				return (D) s;
			} else {
				throw new CannotConvertException("Cannot convert instance of type " + s.getClass()
						+ " to " + targetClass);
			}
		}

		List<Conversion> tempConversionList = new ArrayList<Conversion>(conversionList);
		if (!pConverterInstanceSet.isEmpty()) {
			Map<String, P> convertorsMap = XBeanUtils
					.createPropertyConverterMap(pConverterInstanceSet);
			tempConversionList.add(0, new CustomPropertyConversion<P>(convertorsMap));
		}
		try {

			Map<String, String> fieldsMap = XBeanUtils.createTargetFieldsMap(targetClass);
			for (S pSourceInstance : pSourceInstances) {

				Field[] sourceFields = pSourceInstance.getClass().getDeclaredFields();

				for (Field sourceField : sourceFields) {
					if (fieldsMap.containsKey(sourceField.getName())) {
						Field targetField = targetClass.getDeclaredField(fieldsMap.get(sourceField
								.getName()));

						targetField.setAccessible(true);
						sourceField.setAccessible(true);

						SourceDetails sourceDetails = new SourceDetails(pSourceInstance,
								sourceField);
						TargetDetails targetDetails = new TargetDetails(pTargetInstance,
								targetField);

						for (Conversion conversion : tempConversionList) {
							if (conversion.convert(sourceDetails, targetDetails)) {
								break;
							}
						}
					}
				}
			}
		} catch (Exception e) {
			throw new CannotConvertException(e);
		}
		return pTargetInstance;
	}
}
