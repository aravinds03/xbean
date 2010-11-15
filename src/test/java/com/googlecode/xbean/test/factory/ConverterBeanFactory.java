package com.googlecode.xbean.test.factory;

import static com.googlecode.xbean.test.factory.FactoryConstants.ACTUAL_LONG_VAR;
import static com.googlecode.xbean.test.factory.FactoryConstants.DIFFERNT_NAME;
import static com.googlecode.xbean.test.factory.FactoryConstants.HEX_LONG_VAR;
import static com.googlecode.xbean.test.factory.FactoryConstants.ID;
import static com.googlecode.xbean.test.factory.FactoryConstants.LONG_VAR;
import static com.googlecode.xbean.test.factory.FactoryConstants.NAME;
import static com.googlecode.xbean.test.factory.FactoryConstants.STRING;

import java.util.Calendar;

import com.googlecode.xbean.test.bean.customconvert.ClientBeanCustom;

public class ConverterBeanFactory {

	public static ClientBeanCustom newClientBeanCustom() {
		Calendar instance = Calendar.getInstance();
		instance.set(1971, 0, 22);
		ClientBeanCustom c = new ClientBeanCustom(ID, NAME, DIFFERNT_NAME, LONG_VAR,
				ACTUAL_LONG_VAR, HEX_LONG_VAR, STRING, instance.getTime());

		return c;
	}
}
