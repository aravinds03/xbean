package com.googlecode.xbean.converters.impl;

import com.googlecode.xbean.converters.PropertyConverter;

/**
 * This is just a default convertor which does nothing. Since the annotation
 * attribute of convertibe is mandatory, this class will be the default value.
 * 
 * @author spradeep
 * 
 * @version $Revision: 1.0 $
 */
public class DefaultConvertor implements PropertyConverter<Object, Object> {

	/**
	 * Method convert.
	 * 
	 * @param object
	 *            Object
	 * @return Object
	 * @throws Exception
	 */
	public Object convert(Object object) throws Exception {
		return object;
	}

}
