/**
 * 
 */
package com.googlecode.xbean.conversion.impl;

import com.googlecode.xbean.conversion.Conversion;
import com.googlecode.xbean.conversion.SourceDetails;
import com.googlecode.xbean.conversion.TargetDetails;

/**
 * @author asanthan
 * 
 */
public class SerialVersionIgnoreConversion implements Conversion {

	private static final String SERIAL_VERSION_FIELD_NAME = "serialVersionUID";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.googlecode.xbean.conversion.Conversion#convert(com.googlecode.xbean
	 * .conversion.SourceDetails, com.googlecode.xbean.conversion.TargetDetails)
	 */
	public boolean convert(SourceDetails sourceDetails, TargetDetails targetDetails) {
		return SERIAL_VERSION_FIELD_NAME.equals(targetDetails.getField().getName());
	}

}
