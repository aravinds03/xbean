package com.xbean.test.controller;

import org.junit.Assert;
import org.junit.Test;

import com.xbean.converters.impl.AnnotationBeanConverter;
import com.xbean.test.bean.ClientBean;
import com.xbean.test.bean.ServiceBean;

public class ControllerTest {
	
	@Test
	public void converTest() throws Exception {
		AnnotationBeanConverter annotationBeanConverter = new AnnotationBeanConverter();
		ClientBean c = new ClientBean();
		c.setInteger(123);
		c.setString("Hello");
		ServiceBean convert = annotationBeanConverter.convert(c, ServiceBean.class);
		Assert.assertEquals(convert.getInteger(),123);
		Assert.assertEquals(convert.getString(),"Hello");
	}
}
