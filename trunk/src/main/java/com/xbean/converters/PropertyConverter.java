package com.xbean.converters;

public interface PropertyConverter<S, D> {

	public D convert(S pObject) throws Exception;

	public D convert(S... pObject) throws Exception;
}
