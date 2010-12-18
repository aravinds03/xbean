package com.googlecode.xbean.conversion;


/**
 * @author asanthan
 *
 */
public interface Conversion {
	boolean convert(SourceDetails sourceDetails,TargetDetails targetDetails) throws Exception;
}
