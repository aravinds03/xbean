package com.xbean.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author asanthan
 * 
 */
@Retention(RUNTIME)
@Target(FIELD)
public @interface Converter {

	/**
	 * Specify string whose value is equal to the property name of converter in
	 * the bean.
	 * 
	 * @return
	 */
	String value() default "";

}
