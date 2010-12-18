/**
 * 
 */
package com.googlecode.xbean.conversion.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import com.googlecode.xbean.conversion.Conversion;
import com.googlecode.xbean.conversion.SourceDetails;
import com.googlecode.xbean.conversion.TargetDetails;
import com.googlecode.xbean.converters.BeanConverter;

/**
 * @author asanthan
 * 
 */
public class ListPropertyConversion implements Conversion {

	private BeanConverter converter;

	public ListPropertyConversion(BeanConverter converter) {
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
		if (sourceFieldObj instanceof List<?>) {
			ParameterizedType dparamType = (ParameterizedType) targetField
					.getGenericType();
			Object targetPropertyObject = convertToList(
					(List<?>) sourceFieldObj,
					(Class<?>) dparamType.getActualTypeArguments()[0]);
			targetField.set(targetDetails.getInstance(), targetPropertyObject);
			return true;
		}
		return false;
	}

	public  <S, D> List<D> convertToList(List<S> pSourceList,
			Class<D> pDestination) throws Exception {
		if (pDestination == null) {
			throw new NullPointerException("Parameter pDestination is null");
		}

		List<D> destList = new ArrayList<D>(pSourceList.size());
		if (pSourceList != null) {
			for (S source : pSourceList) {
				destList.add(converter.convert(pDestination, new Object[] { source }));
			}
		}
		return destList;
	}

}
