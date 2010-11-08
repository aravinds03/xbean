package com.xbean.converters;

import java.util.List;
import java.util.Set;

import com.xbean.exceptions.CannotConvertException;

public interface BeanConverter {

	/**
	 * XBean method to convert the source object into a new instance of the
	 * given destination class.
	 * 
	 * @param <S> Source Class
	 * @param <D> Target Class
	 * @param pSourceObject the source object
	 * @param pTargetClass The class of the target object
	 * @return A populated new instance of the target class.
	 * @throws CannotConvertException
	 */
	<S, D> D convert(Class<D> pTargetClass, S... pSourceObject) throws CannotConvertException;
	
	
	/**
	 * XBean method to populate the given target object using the source object.
	 * 
	 * @param <S> Source Class
	 * @param <D> Target Class
	 * @param pSourceObject the source object
	 * @param pTargetObject an instance of the target object.
	 * @throws CannotConvertException
	 */
	<S, D> void populate(D pTargetObject, S... pSourceObject) throws CannotConvertException;

	<S, D> List<D> convertToList(List<S> pSourceList, Class<D> pDestination) throws Exception;

	<S, D> Set<D> convertToSet(Set<S> pSourceSet, Class<D> pDestination) throws Exception;
}
