package com.xbean.converters.impl;

import com.xbean.converters.PropertyConverter;

/**
 * This is just a default convertor which does nothing. Since the annotation
 * attribute of convertibe is mandatory, this class will be the default value.
 * 
 * @author spradeep
 * 
 */
public class DefaultConvertor implements PropertyConverter<Object, Object> {

	public Object convert(Object object) throws Exception {
		return object;
	}

}
