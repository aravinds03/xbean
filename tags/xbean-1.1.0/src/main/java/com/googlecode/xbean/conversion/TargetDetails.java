package com.googlecode.xbean.conversion;

import java.lang.reflect.Field;

public class TargetDetails {

	private Object instance;
	private Field field;
	private Object fieldObject;
	private Class<? extends Object> targetClass;
	
	public TargetDetails(Object instance, Field field) throws Exception {
		super();
		this.instance = instance;
		this.field = field;
		this.targetClass = instance.getClass();
		this.fieldObject = field.get(instance);
	}
	
	
	/**
	 * @return the instance
	 */
	public Object getInstance() {
		return instance;
	}


	/**
	 * @param instance the instance to set
	 */
	public void setInstance(Object instance) {
		this.instance = instance;
	}


	/**
	 * @return the fieldObject
	 */
	public Object getFieldObject() {
		return fieldObject;
	}


	/**
	 * @param fieldObject the fieldObject to set
	 */
	public void setFieldObject(Object fieldObject) {
		this.fieldObject = fieldObject;
	}


	/**
	 * @param targetClass the targetClass to set
	 */
	public void setTargetClass(Class<? extends Object> targetClass) {
		this.targetClass = targetClass;
	}


	/**
	 * @return the field
	 */
	public Field getField() {
		return field;
	}
	
	/**
	 * @param field the field to set
	 */
	public void setField(Field field) {
		this.field = field;
	}
	/**
	 * @return the targetClass
	 */
	public Class<? extends Object> getTargetClass() {
		return targetClass;
	}
	
	
}
