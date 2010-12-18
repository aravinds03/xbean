package com.googlecode.xbean.converters;

import java.util.List;
import java.util.Set;

import com.googlecode.xbean.exceptions.CannotConvertException;

/**
 * Interface which declares method that are required for bean conversion.
 */
public interface BeanConverter {

	/**
	 * XBean method to convert the source object into a new instance of the
	 * given destination class.
	 * 
	 * 
	 * 
	 * @param pSourceObject
	 *            the source object
	 * @param pTargetClass
	 *            The class of the target object
	 * 
	 * 
	 * @return A populated new instance of the target class. * @throws
	 *         CannotConvertException
	 */
	<S, D> D convert(Class<D> pTargetClass, S... pSourceObject) throws CannotConvertException;

	/**
	 * XBean method to populate the given target object using the source object.
	 * 
	 * 
	 * 
	 * @param pSourceObject
	 *            the source object
	 * @param pTargetObject
	 *            an instance of the target object.
	 * 
	 * @throws CannotConvertException
	 */
	<S, D> D convertByInstance(D pTargetObject, S... pSourceObject)
			throws CannotConvertException;

	/**
	 * Method convertToList.
	 * 
	 * @param pSourceList
	 *            List<S>
	 * @param pDestination
	 *            Class<D>
	 * @return List<D>
	 * @throws Exception
	 */
	<S, D> List<D> convertToList(List<S> pSourceList, Class<D> pDestination) throws Exception;

	/**
	 * Method convertToSet.
	 * 
	 * @param pSourceSet
	 *            Set<S>
	 * @param pDestination
	 *            Class<D>
	 * @return Set<D>
	 * @throws Exception
	 */
	<S, D> Set<D> convertToSet(Set<S> pSourceSet, Class<D> pDestination) throws Exception;

	/**
	 * Method convertByInstanceConverter.
	 * 
	 * @param pTargetInstance
	 *            D
	 * @param pConverterInstanceSet
	 *            Set
	 *            <P>
	 * @param pSourceInstances
	 *            S[]
	 * @throws CannotConvertException
	 */
	<S, D, P extends PropertyConverter<?, ?>> D convertByInstanceConverter(D pTargetInstance,
			Set<P> pConverterInstanceSet, S... pSourceInstances) throws CannotConvertException;
}
