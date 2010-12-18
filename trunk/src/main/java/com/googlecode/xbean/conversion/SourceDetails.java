package com.googlecode.xbean.conversion;

import java.lang.reflect.Field;

public class SourceDetails {
	private Object instance;
	private Field field;
	private Object fieldObject;
	private Class<?> sourceClass;
	
	public SourceDetails(Object instance, Field field) throws Exception {
		super();
		this.instance = instance;
		this.field = field;
		this.sourceClass = instance.getClass();
		this.fieldObject = 	field.get(instance);
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
	 * @return the sourceClass
	 */
	public Class<?> getSourceClass() {
		return sourceClass;
	}
	/**
	 * @param sourceClass the sourceClass to set
	 */
	public void setSourceClass(Class<?> sourceClass) {
		this.sourceClass = sourceClass;
	}
	
	
	

}
