package com.xbean.converters;

/**
 * Interface for property converter's. Implement this interface to define your
 * custom property conversion in a bean.
 * 
 * For example: You may need to convert Date property in one bean to String
 * property in other bean. In such case, you can create your own converter and
 * implement your logic in the respective overriden methods.
 * 
 * @author asanthan
 * 
 * @param <S>
 *            - Source property type.
 * @param <D>
 *            - Destination property type.
 */
public interface PropertyConverter<S, D> {

	/**
	 * Method to convert the given Object of type S to object of type D.
	 * 
	 * @param pObject
	 *            - source object to convert.
	 * @return D - return the converted object.
	 * @throws Exception
	 */
	public D convert(S pObject) throws Exception;
}
