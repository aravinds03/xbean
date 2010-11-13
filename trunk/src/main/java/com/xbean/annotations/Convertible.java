package com.xbean.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.xbean.converters.impl.DefaultConvertor;

/**
 * Use this annotation if property name in destination bean differs from source
 * bean.
 * 
 * @author asanthan
 * 
 */
@Retention(RUNTIME)
@Target(FIELD)
public @interface Convertible {

	/**
	 * String represents the property name from the source bean.
	 * 
	 * @return
	 */
	String value() default "";
	
	/**
	 * The converter class.
	 * 
	 * @return
	 */
	Class<?> convertor() default DefaultConvertor.class;
}
