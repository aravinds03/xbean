package com.xbean.converters.impl;

import static com.xbean.test.factory.ConverterBeanFactory.newClientBeanCustom;
import static com.xbean.test.factory.FactoryConstants.DATE_FORMAT;
import static com.xbean.test.factory.FactoryConstants.DIFFERNT_NAME;
import static com.xbean.test.factory.FactoryConstants.DIFFERNT_NAME1;
import static com.xbean.test.factory.FactoryConstants.DIFFERNT_NAME3;
import static com.xbean.test.factory.FactoryConstants.HEX_STRING;
import static com.xbean.test.factory.FactoryConstants.ID;
import static com.xbean.test.factory.FactoryConstants.ID2;
import static com.xbean.test.factory.FactoryConstants.IGNORED_LONG_VAR;
import static com.xbean.test.factory.FactoryConstants.LONG_VAR;
import static com.xbean.test.factory.FactoryConstants.NAME;
import static com.xbean.test.factory.FactoryConstants.SOME_STRING;
import static com.xbean.test.factory.FactoryConstants.STRING2;
import static com.xbean.test.factory.MultiBeanFactory.newServiceBean1;
import static com.xbean.test.factory.MultiBeanFactory.newServiceBean2;
import static com.xbean.test.factory.SingleBeanFactory.newClientBean;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.xbean.converters.BeanConverter;
import com.xbean.converters.PropertyConverter;
import com.xbean.test.AppendConvertor;
import com.xbean.test.HexConvertor;
import com.xbean.test.bean.ServiceBean;
import com.xbean.test.bean.customconvert.ServiceBeanCustom;
import com.xbean.test.bean.multi.CompositeClientBean;

public class AnnotationBeanConverterTest {

	@Test
	public void testConvertToList() {
		// fail("Not yet implemented");
	}

	@Test
	public void testConvertToSet() {
		// fail("Not yet implemented");
	}

	@Test
	public void testConvert() {
		BeanConverter annotationBeanConverter = new AnnotationBeanConverter();
		ServiceBean convert = annotationBeanConverter.convert(ServiceBean.class, newClientBean());
		Assert.assertEquals(ID, convert.getId());
		Assert.assertEquals(NAME, convert.getName());
		Assert.assertEquals(DIFFERNT_NAME, convert.getDifferentName1());
		Assert.assertEquals(LONG_VAR, convert.getLongVar());
		Assert.assertEquals(IGNORED_LONG_VAR, convert.getIgnoredLongVar());
	}

	@Test
	public void testConvertByInstance() {
		BeanConverter annotationBeanConverter = new AnnotationBeanConverter();
		ServiceBean convert = new ServiceBean();
		annotationBeanConverter.convertByInstance(convert, newClientBean());
		Assert.assertEquals(ID, convert.getId());
		Assert.assertEquals(NAME, convert.getName());
		Assert.assertEquals(DIFFERNT_NAME, convert.getDifferentName1());
		Assert.assertEquals(LONG_VAR, convert.getLongVar());
		Assert.assertEquals(IGNORED_LONG_VAR, convert.getIgnoredLongVar());
	}

	@Test
	public void testConvert_MultipleBeans() {
		BeanConverter annotationBeanConverter = new AnnotationBeanConverter();

		CompositeClientBean c = annotationBeanConverter.convert(CompositeClientBean.class,
				newServiceBean1(), newServiceBean2());

		Assert.assertEquals(ID, c.getId());
		Assert.assertEquals(ID2, c.getId2());
		Assert.assertEquals(DIFFERNT_NAME1, c.getDifferentName2());
		Assert.assertEquals(DIFFERNT_NAME3, c.getDifferentName4());
		Assert.assertEquals(SOME_STRING, c.getString());
		Assert.assertEquals(STRING2, c.getString2());
		Assert.assertEquals(null, c.getIgnoredString());
	}

	@Test
	public void testConvertByInstance_MultipleBeans() {
		BeanConverter annotationBeanConverter = new AnnotationBeanConverter();

		CompositeClientBean c = new CompositeClientBean();
		annotationBeanConverter.convertByInstance(c, newServiceBean1(), newServiceBean2());

		Assert.assertEquals(ID, c.getId());
		Assert.assertEquals(ID2, c.getId2());
		Assert.assertEquals(DIFFERNT_NAME1, c.getDifferentName2());
		Assert.assertEquals(DIFFERNT_NAME3, c.getDifferentName4());
		Assert.assertEquals(SOME_STRING, c.getString());
		Assert.assertEquals(STRING2, c.getString2());
		Assert.assertEquals(null, c.getIgnoredString());
	}

	@Test
	public void testConvertByInstanceConverter() {
		BeanConverter annotationBeanConverter = new AnnotationBeanConverter();

		ServiceBeanCustom convert = new ServiceBeanCustom();
		Set<PropertyConverter<?, ?>> converterSet = new HashSet<PropertyConverter<?, ?>>();
		converterSet.add(new HexConvertor());
		converterSet.add(new AppendConvertor());
		converterSet.add(new DatePropertyConverter());
		annotationBeanConverter.convertByInstanceConverter(convert, converterSet,
				newClientBeanCustom());

		Assert.assertEquals(ID, convert.getId());
		Assert.assertEquals(NAME, convert.getString());
		Assert.assertEquals(DIFFERNT_NAME, convert.getDifferentName1());
		Assert.assertEquals(LONG_VAR, convert.getLongVar());
		Assert.assertEquals(IGNORED_LONG_VAR, convert.getIgnoredLongVar());
		Assert.assertEquals(HEX_STRING, convert.getHexLong());
		Assert.assertEquals("string converted!!!!", convert.getDiffNameConvertor123());
		Assert.assertEquals(DATE_FORMAT, convert.getDiffNameConvertorFromDate());

	}
	// TODO: Add tests for cases where you have same field names for different
	// types of members in different beans
	// TODO: Add tests with convertor.
	// TODO: add tests for collection data types
	// TODO: Add tests for hierarchial classes/objects
}
