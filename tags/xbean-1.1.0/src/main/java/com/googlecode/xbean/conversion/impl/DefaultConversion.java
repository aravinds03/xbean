/**
 * 
 */
package com.googlecode.xbean.conversion.impl;

import java.lang.reflect.Field;

import com.googlecode.xbean.conversion.Conversion;
import com.googlecode.xbean.conversion.SourceDetails;
import com.googlecode.xbean.conversion.TargetDetails;

/**
 * @author asanthan
 * 
 */
public class DefaultConversion implements Conversion {

	public DefaultConversion() {
		super();
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
		Object targetPropertyObject = sourceDetails.getFieldObject();
		targetField.set(targetDetails.getInstance(), targetPropertyObject);
		return false; //NOTE: Always return false, so that it will forward to next conversion.
	}
}
