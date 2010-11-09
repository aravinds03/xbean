package com.xbean.test.controller;

import org.junit.Assert;
import org.junit.Test;

import com.xbean.converters.impl.AnnotationBeanConverter;
import com.xbean.test.bean.ClientBean;
import com.xbean.test.bean.ServiceBean;
import com.xbean.test.bean.multi.CompositeClientBean;
import com.xbean.test.bean.multi.ServiceBeanOne;
import com.xbean.test.bean.multi.ServiceBeanTwo;

public class XBeanTest {
	
	@Test
	public void converTest() throws Exception {
		AnnotationBeanConverter annotationBeanConverter = new AnnotationBeanConverter();
		ClientBean c = new ClientBean();
		c.setInteger(123);
		c.setString("Hello");
		c.setDifferentName2(2500);
		c.setLongVar(20000000000l);
		c.setIgnoredLongVar(3333333l);
		ServiceBean convert = annotationBeanConverter.convert(ServiceBean.class,c);
		Assert.assertEquals(123,convert.getInteger());
		Assert.assertEquals("Hello",convert.getString());
		Assert.assertEquals(2500,convert.getDifferentName1());
		Assert.assertEquals(20000000000l, convert.getLongVar());
		Assert.assertEquals(0l, convert.getIgnoredLongVar());
	}
	
	@Test
	public void converTestPopulate() throws Exception {
		AnnotationBeanConverter annotationBeanConverter = new AnnotationBeanConverter();
		ClientBean c = new ClientBean();
		c.setInteger(123);
		c.setString("Hello");
		c.setDifferentName2(2500);
		c.setLongVar(20000000000l);
		c.setIgnoredLongVar(3333333l);
		ServiceBean convert = new ServiceBean();
		annotationBeanConverter.convertByInstance(convert,c);
		Assert.assertEquals(123,convert.getInteger());
		Assert.assertEquals("Hello",convert.getString());
		Assert.assertEquals(2500,convert.getDifferentName1());
		Assert.assertEquals(20000000000l, convert.getLongVar());
		Assert.assertEquals(0l, convert.getIgnoredLongVar());
	}
	
	@Test
	public void convertCompositeTest() throws Exception {
		AnnotationBeanConverter annotationBeanConverter = new AnnotationBeanConverter();
		
		ServiceBeanOne s1 = new ServiceBeanOne();
		s1.setInteger(123);
		s1.setDifferentName1(222);
		s1.setString("somestring");
		s1.setIgnoredString("ignoredString");
		
		ServiceBeanTwo s2 = new ServiceBeanTwo();
		s2.setInteger2(333);
		s2.setDifferentName3(444);
		s2.setString2("string2");
		
		
		CompositeClientBean c= new CompositeClientBean();
		annotationBeanConverter.convertByInstance(c,s1,s2);
		//annotationBeanConverter.populate(s2, c);
		
		Assert.assertEquals(123,c.getInteger());
		Assert.assertEquals(222, c.getDifferentName2());

		Assert.assertEquals(333,c.getInteger2());
		Assert.assertEquals(444, c.getDifferentName4());
		
		Assert.assertEquals("somestring",c.getString());
		Assert.assertEquals("string2", c.getString2());
		
		Assert.assertEquals(null, c.getIgnoredString());
		
	}
	
	// TODO: Add tests for cases where you have same field names for different types of members in different beans
	// TODO: Add tests with convertor.
	// TODO: add tests for collection data types
	// TODO: Add tests for hierarchial classes/objects
}
