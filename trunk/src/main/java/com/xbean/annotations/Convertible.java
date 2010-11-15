package com.xbean.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.xbean.converters.PropertyConverter;
import com.xbean.converters.impl.DefaultConvertor;

/**
 * Use this annotation if property name in destination bean differs from source
 * bean.
 * 
 * @author asanthan
 * 
 * @version $Revision: 1.0.2 $
 */
@Retention(RUNTIME)
@Target(FIELD)
public @interface Convertible {

	/**
	 * String represents the property name from the source bean.
	 * 
	
	 * @return String
	 */
	String value() default "";

	/**
	 * The converter class. Specify property converter implementation class.
	 * 
	
	 * @return Class<? extends PropertyConverter<?,?>>
	 */
	Class<? extends PropertyConverter<?, ?>> convertor() default DefaultConvertor.class;

	/**
	 * if true, assumes the property type is user defined bean and applies the
	 * conversion automatically.
	 * 
	
	 * @return boolean
	 */
	boolean auto() default false;
}
