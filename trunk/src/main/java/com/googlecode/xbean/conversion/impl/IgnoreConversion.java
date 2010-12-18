/**
 * 
 */
package com.googlecode.xbean.conversion.impl;

import com.googlecode.xbean.annotations.Ignore;
import com.googlecode.xbean.conversion.Conversion;
import com.googlecode.xbean.conversion.SourceDetails;
import com.googlecode.xbean.conversion.TargetDetails;

/**
 * @author asanthan
 * 
 */
public class IgnoreConversion implements Conversion {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.googlecode.xbean.conversion.Conversion#convert(com.googlecode.xbean
	 * .conversion.SourceDetails, com.googlecode.xbean.conversion.TargetDetails)
	 */
	public boolean convert(SourceDetails sourceDetails, TargetDetails targetDetails) {
		return targetDetails.getField().isAnnotationPresent(Ignore.class);
	}

}
