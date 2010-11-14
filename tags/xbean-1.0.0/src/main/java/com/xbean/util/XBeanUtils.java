/**
 * 
 */
package com.xbean.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

	public static boolean isEmptyString(String pString) {
		return pString == null || pString.equals("") || pString.trim().equals("");
	}
}
