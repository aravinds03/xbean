/**
 * 
 */
package com.xbean.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.xbean.annotations.Convertible;
import com.xbean.annotations.Ignore;
import com.xbean.converters.PropertyConverter;

/**
 * @author asanthan
 * 
 */
public class XBeanUtils {

	public static <P extends PropertyConverter<?, ?>> Map<String, P> createPropertyConverterMap(
			Set<P> pConverterInstanceSet) {
		if (pConverterInstanceSet == null) {
			pConverterInstanceSet = new HashSet<P>(1);
		}
		Map<String, P> converterMap = new HashMap<String, P>(pConverterInstanceSet.size());
		for (P converter : pConverterInstanceSet) {
			System.out.println("Inserting key=" + converter.getClass().getName());
			converterMap.put(converter.getClass().getName(), converter);
		}
		return converterMap;
	}

	public static Map<String, String> createTargetFieldsMap(Class<? extends Object> pTargetClass) {
		Field[] targetFields = pTargetClass.getDeclaredFields();

		HashMap<String, String> fieldsMap = new HashMap<String, String>(targetFields.length);

		for (Field field : targetFields) {
			if (field.getAnnotation(Ignore.class) != null) {
				continue;
			}
			Convertible targetAnnotation = field.getAnnotation(Convertible.class);
			String sourceFieldName = null;
			if (targetAnnotation != null && !XBeanUtils.isEmptyString(targetAnnotation.value())) {
				sourceFieldName = targetAnnotation.value();
			} else {
				sourceFieldName = field.getName();
			}
			fieldsMap.put(sourceFieldName, field.getName());
		}
		return fieldsMap;
	}

	public static boolean isEmptyString(String pString) {
		return pString == null || pString.trim().equals("");
	}
}
