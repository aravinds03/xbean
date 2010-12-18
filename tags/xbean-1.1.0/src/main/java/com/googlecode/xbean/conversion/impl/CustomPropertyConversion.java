/**
 * 
 */
package com.googlecode.xbean.conversion.impl;

import java.lang.reflect.Field;
import java.util.Map;

import com.googlecode.xbean.annotations.Convertible;
import com.googlecode.xbean.conversion.Conversion;
import com.googlecode.xbean.conversion.SourceDetails;
import com.googlecode.xbean.conversion.TargetDetails;
import com.googlecode.xbean.converters.PropertyConverter;
import com.googlecode.xbean.converters.impl.DefaultConvertor;

/**
 * @author asanthan
 * @param <P>
 *
 */
public class CustomPropertyConversion<P extends PropertyConverter<?,?>> implements Conversion {

	/* (non-Javadoc)
	 * @see com.googlecode.xbean.conversion.Conversion#convert(com.googlecode.xbean.conversion.SourceDetails, com.googlecode.xbean.conversion.TargetDetails)
	 */
	private Map<String, P> convertorsMap;
	public CustomPropertyConversion(Map<String, P> convertorsMap2) {
		super();
		this.convertorsMap = convertorsMap2;
	}
	public boolean convert(SourceDetails sourceDetails,
			TargetDetails targetDetails) throws Exception {
		// check custom converter property.
		
		Field targetField = targetDetails.getField();
		String customPropertyConverter = getConverterClassName(targetField);
		
		if(customPropertyConverter!= null) {
			P pConverter = convertorsMap.get(customPropertyConverter);
			Object targetPropertyObject = ((PropertyConverter<Object, Object>)pConverter).convert(sourceDetails.getFieldObject());
			targetField.set(targetDetails.getInstance(), targetPropertyObject);
			return true;
		} 
		return false;
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
