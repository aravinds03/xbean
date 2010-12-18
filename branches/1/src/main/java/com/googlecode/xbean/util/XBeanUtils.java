/**
 * 
 */
package com.googlecode.xbean.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.googlecode.xbean.annotations.Convertible;
import com.googlecode.xbean.annotations.Ignore;
import com.googlecode.xbean.converters.PropertyConverter;

/**
 * It contains utility methods.
 * @author asanthan
 * 
 * @version $Revision: 1.0 $
 */
public class XBeanUtils {

	/**
	 * Method createPropertyConverterMap.
	 * 
	 * @param pConverterInstanceSet
	 *            Set
	 *            <P>
	 * @return Map<String,P>
	 */
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

	/**
	 * Method createTargetFieldsMap.
	 * 
	 * @param pTargetClass
	 *            Class<? extends Object>
	 * @return Map<String,String>
	 */
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

	/**
	 * Method isEmptyString.
	 * 
	 * @param pString
	 *            String
	 * @return boolean
	 */
	public static boolean isEmptyString(String pString) {
		return pString == null || pString.trim().equals("");
	}
}
