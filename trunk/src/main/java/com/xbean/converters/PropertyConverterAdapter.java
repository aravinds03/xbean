package com.xbean.converters;

public class PropertyConverterAdapter<S extends Object, D extends Object> implements
		PropertyConverter<S, D> {

	@Override
	public D convert(S pObject) throws Exception {
		return null;
	}

	@Override
	public D convert(S... pObject) throws Exception {
		return null;
	}

}
