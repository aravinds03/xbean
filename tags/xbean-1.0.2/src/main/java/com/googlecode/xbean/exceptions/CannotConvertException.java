package com.googlecode.xbean.exceptions;

/**
 * Custom exception to throw during conversion process.
 * 
 * @author asanthan
 * 
 * @version $Revision: 1.0 $
 */
public class CannotConvertException extends RuntimeException {

	/**
	 * Constructor for CannotConvertException.
	 * 
	 * @param e
	 *            Throwable
	 */
	public CannotConvertException(Throwable e) {
		super(e);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 8395739202422434417L;

}
