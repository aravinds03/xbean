/**
 * 
 */
package com.googlecode.xbean.conversion.impl;

import java.lang.reflect.Field;

import com.googlecode.xbean.annotations.Convertible;
import com.googlecode.xbean.conversion.Conversion;
import com.googlecode.xbean.conversion.SourceDetails;
import com.googlecode.xbean.conversion.TargetDetails;
import com.googlecode.xbean.converters.BeanConverter;

/**
 * @author asanthan
 * 
 */
public class AutoAnnotationConversion implements Conversion {

	private BeanConverter converter;

	public AutoAnnotationConversion(BeanConverter converter) {
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
			TargetDetails targetDetails)
			throws Exception {
		Field targetField = targetDetails.getField();
		// check auto property.
		if (isAutoAvailable(targetField)) {
			Object targetFieldObject = converter.convert(targetField.getType(),sourceDetails.getFieldObject());
			targetField.set(targetDetails.getInstance(), targetFieldObject);
			return true;
		}
		return false;
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
		Convertible targetAnnotation = pTargetField
				.getAnnotation(Convertible.class);
		return targetAnnotation != null && targetAnnotation.auto();
	}

}
