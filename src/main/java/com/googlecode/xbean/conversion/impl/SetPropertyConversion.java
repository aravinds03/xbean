/**
 * 
 */
package com.googlecode.xbean.conversion.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.HashSet;
import java.util.Set;

import com.googlecode.xbean.conversion.Conversion;
import com.googlecode.xbean.conversion.SourceDetails;
import com.googlecode.xbean.conversion.TargetDetails;
import com.googlecode.xbean.converters.BeanConverter;

/**
 * @author asanthan
 * 
 */
public class SetPropertyConversion implements Conversion {

	private BeanConverter converter;

	public SetPropertyConversion(BeanConverter converter) {
		super();
		this.converter = converter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.googlecode.xbean.conversion.Conversion#convert(com.googlecode.xbean
	 * .conversion.SourceDetails, com.googlecode.xbean.conversion.TargetDetails)
	 */
	public boolean convert(SourceDetails sourceDetails,
			TargetDetails targetDetails) throws Exception {
		Field targetField = targetDetails.getField();
		Object sourceFieldObj = sourceDetails.getFieldObject();
		if (sourceFieldObj instanceof Set<?>) {
			ParameterizedType dparamType = (ParameterizedType) targetField
					.getGenericType();
			Object targetPropertyObject = convertToSet(
					(Set<?>) sourceFieldObj,
					(Class<?>) dparamType.getActualTypeArguments()[0]);
			targetField.set(targetDetails.getInstance(), targetPropertyObject);
			return true;
		}
		return false;
	}
	
	public <S, D> Set<D> convertToSet(Set<S> pSourceSet, Class<D> pDestination) throws Exception {
		if (pDestination == null) {
			throw new NullPointerException("Parameter pDestination is null");
		}

		Set<D> destSet = new HashSet<D>(pSourceSet.size());
		if (pSourceSet != null) {
			for (S source : pSourceSet) {
				destSet.add(converter.convert(pDestination, new Object[] { source }));
			}
		}
		return destSet;
	}


}
