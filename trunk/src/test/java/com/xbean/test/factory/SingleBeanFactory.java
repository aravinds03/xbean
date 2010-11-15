package com.xbean.test.factory;

import static com.xbean.test.factory.FactoryConstants.ACTUAL_LONG_VAR;
import static com.xbean.test.factory.FactoryConstants.DIFFERNT_NAME;
import static com.xbean.test.factory.FactoryConstants.ID;
import static com.xbean.test.factory.FactoryConstants.LONG_VAR;
import static com.xbean.test.factory.FactoryConstants.NAME;

import com.xbean.test.bean.ClientBean;

public class SingleBeanFactory {

	public static ClientBean newClientBean() {
		ClientBean c = new ClientBean();
		c.setId(ID);
		c.setName(NAME);
		c.setDifferentName(DIFFERNT_NAME);
		c.setLongVar(LONG_VAR);
		c.setIgnoredLongVar(ACTUAL_LONG_VAR);
		return c;
	}

}
