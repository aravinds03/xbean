package com.googlecode.xbean.test.factory;

import static com.googlecode.xbean.test.factory.FactoryConstants.DIFFERNT_NAME1;
import static com.googlecode.xbean.test.factory.FactoryConstants.DIFFERNT_NAME3;
import static com.googlecode.xbean.test.factory.FactoryConstants.ID;
import static com.googlecode.xbean.test.factory.FactoryConstants.ID2;
import static com.googlecode.xbean.test.factory.FactoryConstants.IGNORED_STRING;
import static com.googlecode.xbean.test.factory.FactoryConstants.SOME_STRING;
import static com.googlecode.xbean.test.factory.FactoryConstants.STRING2;

import com.googlecode.xbean.test.bean.multi.ServiceBeanOne;
import com.googlecode.xbean.test.bean.multi.ServiceBeanTwo;

public class MultiBeanFactory {
	public static ServiceBeanOne newServiceBean1() {
		ServiceBeanOne s1 = new ServiceBeanOne();
		s1.setId(ID);
		s1.setDifferentName1(DIFFERNT_NAME1);
		s1.setString(SOME_STRING);
		s1.setIgnoredString(IGNORED_STRING);
		return s1;
	}

	public static ServiceBeanTwo newServiceBean2() {
		ServiceBeanTwo s2 = new ServiceBeanTwo();
		s2.setId2(ID2);
		s2.setDifferentName3(DIFFERNT_NAME3);
		s2.setString2(STRING2);
		return s2;
	}
}
