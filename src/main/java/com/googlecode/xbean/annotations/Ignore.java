package com.googlecode.xbean.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Annotation to notify bean converter to not convert this property. Generally,
 * property which holds property converter must have this annotation.
 * 
 * @author asanthan
 * 
 * @version $Revision: 1.0 $
 */
@Retention(RUNTIME)
@Target(FIELD)
public @interface Ignore {
}
