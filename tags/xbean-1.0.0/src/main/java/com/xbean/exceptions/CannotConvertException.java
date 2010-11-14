package com.xbean.exceptions;

/**
 * Custom exception to throw during conversion process.
 * 
 * @author asanthan
 * 
 */
public class CannotConvertException extends RuntimeException {

	public CannotConvertException(Throwable e) {
		super(e);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 8395739202422434417L;

}
