package com.googlecode.xbean.converters.impl;

import static com.googlecode.xbean.test.factory.ConverterBeanFactory.newClientBeanCustom;
import static com.googlecode.xbean.test.factory.FactoryConstants.DATE_FORMAT;
import static com.googlecode.xbean.test.factory.FactoryConstants.DIFFERNT_NAME;
import static com.googlecode.xbean.test.factory.FactoryConstants.DIFFERNT_NAME1;
import static com.googlecode.xbean.test.factory.FactoryConstants.DIFFERNT_NAME3;
import static com.googlecode.xbean.test.factory.FactoryConstants.HEX_STRING;
import static com.googlecode.xbean.test.factory.FactoryConstants.ID;
import static com.googlecode.xbean.test.factory.FactoryConstants.ID2;
import static com.googlecode.xbean.test.factory.FactoryConstants.IGNORED_LONG_VAR_ZERO;
import static com.googlecode.xbean.test.factory.FactoryConstants.INT_ARRAY;
import static com.googlecode.xbean.test.factory.FactoryConstants.LONG_VAR;
import static com.googlecode.xbean.test.factory.FactoryConstants.NAME;
import static com.googlecode.xbean.test.factory.FactoryConstants.SOME_STRING;
import static com.googlecode.xbean.test.factory.FactoryConstants.STRING2;
import static com.googlecode.xbean.test.factory.MultiBeanFactory.newServiceBean1;
import static com.googlecode.xbean.test.factory.MultiBeanFactory.newServiceBean2;
import static com.googlecode.xbean.test.factory.SingleBeanFactory.newClientBean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.googlecode.xbean.conversion.Conversion;
import com.googlecode.xbean.conversion.impl.SerialVersionIgnoreConversion;
import com.googlecode.xbean.converters.BeanConverter;
import com.googlecode.xbean.converters.PropertyConverter;
import com.googlecode.xbean.test.AppendConvertor;
import com.googlecode.xbean.test.HexConvertor;
import com.googlecode.xbean.test.bean.ClientBean;
import com.googlecode.xbean.test.bean.ServiceBean;
import com.googlecode.xbean.test.bean.collection.CollectionClientBean;
import com.googlecode.xbean.test.bean.collection.CollectionServiceBean;
import com.googlecode.xbean.test.bean.customconvert.ClientBeanCustom;
import com.googlecode.xbean.test.bean.customconvert.ServiceBeanCustom;
import com.googlecode.xbean.test.bean.multi.CompositeClientBean;
import com.googlecode.xbean.test.factory.CollectionBeanFactory;

public class AnnotationBeanConverterTest {

	/**
	 * Convert a client bean into a ServiceBean object.
	 * A new service bean object gets created.
	 */
	@Test
	public void testConvert() {
		BeanConverter annotationBeanConverter = new AnnotationBeanConverter();
		ServiceBean convert = annotationBeanConverter.convert(ServiceBean.class, newClientBean());
		
		// Assert the fields have same value.
		Assert.assertEquals(ID, convert.getId());
		Assert.assertEquals(NAME, convert.getName());
		Assert.assertEquals(DIFFERNT_NAME, convert.getDifferentName1());
		Assert.assertEquals(LONG_VAR, convert.getLongVar());
		Assert.assertNull(convert.getSomeAlwaysNullObj());

		// Make sure the ignored long varaible is 0
		Assert.assertEquals(IGNORED_LONG_VAR_ZERO, convert.getIgnoredLongVar());
	}

	/**
	 * Convert a client bean into a ServiceBean object.
	 * You already have a service bean object. Conversion just sets the
	 * field values.
	 */
	@Test
	public void testConvertByInstance() {
		BeanConverter annotationBeanConverter = new AnnotationBeanConverter();
		ServiceBean convert = new ServiceBean();
		convert.setIgnoredLongVar(LONG_VAR);
		annotationBeanConverter.convertByInstance(convert, newClientBean());
		
		// Assert the fields have same value.
		Assert.assertEquals(ID, convert.getId());
		Assert.assertEquals(NAME, convert.getName());
		Assert.assertEquals(DIFFERNT_NAME, convert.getDifferentName1());
		Assert.assertEquals(LONG_VAR, convert.getLongVar());
		Assert.assertNull(convert.getSomeAlwaysNullObj());
		
		// Make sure the ignored long varaible is actual and not made zero or changed.
		Assert.assertEquals(LONG_VAR, convert.getIgnoredLongVar());
	}

	/**
	 * Convert two service bean instances into one client bean.
	 * A new client bean object gets created.
	 */
	@Test
	public void testConvert_MultipleBeans() {
		BeanConverter annotationBeanConverter = new AnnotationBeanConverter();

		CompositeClientBean c = annotationBeanConverter.convert(CompositeClientBean.class,
				newServiceBean1(), newServiceBean2());

		// Assert the fields have same value.
		Assert.assertEquals(ID, c.getId());
		Assert.assertEquals(ID2, c.getId2());
		Assert.assertEquals(DIFFERNT_NAME1, c.getDifferentName2());
		Assert.assertEquals(DIFFERNT_NAME3, c.getDifferentName4());
		Assert.assertEquals(SOME_STRING, c.getString());
		Assert.assertEquals(STRING2, c.getString2());
		
		// Make sure the ignored string is not set.
		Assert.assertEquals(null, c.getIgnoredString());
	}

	/**
	 * Convert two service bean instances into one client bean.
	 * You already have a client bean object. Conversion just sets the
	 * field values.
	 */
	@Test
	public void testConvertByInstance_MultipleBeans() {
		BeanConverter annotationBeanConverter = new AnnotationBeanConverter();

		CompositeClientBean c = new CompositeClientBean();
		annotationBeanConverter.convertByInstance(c, newServiceBean1(), newServiceBean2());

		// Assert the fields have same value.
		Assert.assertEquals(ID, c.getId());
		Assert.assertEquals(ID2, c.getId2());
		Assert.assertEquals(DIFFERNT_NAME1, c.getDifferentName2());
		Assert.assertEquals(DIFFERNT_NAME3, c.getDifferentName4());
		Assert.assertEquals(SOME_STRING, c.getString());
		Assert.assertEquals(STRING2, c.getString2());
		
		// Make sure the ignored string is not set.
		Assert.assertEquals(null, c.getIgnoredString());
	}

	/**
	 * Convert a client bean to a service bean. In this test, we use
	 * custom convertors to convert client bean's field to service bean's
	 * field.
	 */
	@Test
	public void testConvertByInstance_CustomConverter() {
		BeanConverter annotationBeanConverter = new AnnotationBeanConverter();

		ServiceBeanCustom convert = new ServiceBeanCustom();
		Set<PropertyConverter<?, ?>> converterSet = new HashSet<PropertyConverter<?, ?>>();
		converterSet.add(new HexConvertor());
		converterSet.add(new AppendConvertor());
		converterSet.add(new DatePropertyConverter());
		annotationBeanConverter.convertByInstanceConverter(convert, converterSet,
				newClientBeanCustom());

		// Assert the fields have expected value.
		Assert.assertEquals(ID, convert.getId());
		Assert.assertEquals(NAME, convert.getString());
		Assert.assertEquals(DIFFERNT_NAME, convert.getDifferentName1());
		Assert.assertEquals(LONG_VAR, convert.getLongVar());
		Assert.assertEquals(IGNORED_LONG_VAR_ZERO, convert.getIgnoredLongVar());
		Assert.assertEquals(HEX_STRING, convert.getHexLong());
		Assert.assertEquals("string converted!!!!", convert.getDiffNameConvertor123());
		Assert.assertEquals(DATE_FORMAT, convert.getDiffNameConvertorFromDate());
		Assert.assertEquals(ClientBeanCustom.getSerialversionuid(),
				ServiceBeanCustom.getSerialversionuid());
	}

	/**
	 * Convert a client bean to a service bean. In this test, we use
	 * custom convertors to convert client bean's field to service bean's
	 * field. This test uses a custom property {@link Conversion} object to the main 
	 * annotation convertor class.
	 */
	@Test
	public void testConvertByInstance_CustomConverter_CustomConversion() {
		AnnotationBeanConverter annotationBeanConverter = new AnnotationBeanConverter();

		List<Conversion> conversionList = new ArrayList<Conversion>(1);
		conversionList.add(new SerialVersionIgnoreConversion());
		annotationBeanConverter.setConversionList(conversionList);

		ServiceBeanCustom convert = new ServiceBeanCustom();
		ServiceBeanCustom.setSerialVersionUID(0);
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
		Assert.assertEquals(IGNORED_LONG_VAR_ZERO, convert.getIgnoredLongVar());
		Assert.assertEquals(HEX_STRING, convert.getHexLong());
		Assert.assertEquals("string converted!!!!", convert.getDiffNameConvertor123());
		Assert.assertEquals(DATE_FORMAT, convert.getDiffNameConvertorFromDate());
		Assert.assertTrue(ClientBeanCustom.getSerialversionuid() != ServiceBeanCustom
				.getSerialversionuid());
	}

	/**
	 * Test converting a list of client beans to another list of service beans.
	 */
	@Test
	public void testConvertToList() throws Exception {
		BeanConverter annotationBeanConverter = new AnnotationBeanConverter();
		List<ClientBean> clientBeanList = CollectionBeanFactory.newClientBeanList();
		List<ServiceBean> beanList = annotationBeanConverter.convertToList(clientBeanList,
				ServiceBean.class);
		Assert.assertEquals(clientBeanList.size(), beanList.size());
		for(int i=0;i<beanList.size();++i) {
			ServiceBean bean = beanList.get(i);
			Assert.assertEquals(ID, bean.getId());
			Assert.assertEquals(NAME+i, bean.getName());
			Assert.assertEquals(DIFFERNT_NAME, bean.getDifferentName1());
			Assert.assertEquals(LONG_VAR, bean.getLongVar());
			Assert.assertEquals(IGNORED_LONG_VAR_ZERO, bean.getIgnoredLongVar());
		}

	}

	/**
	 * Test converting a set of client beans to another set of service beans.
	 */
	@Test
	public void testConvertToSet() throws Exception {
		BeanConverter annotationBeanConverter = new AnnotationBeanConverter();
		Set<ClientBean> clientBeanSet = CollectionBeanFactory.newClientBeanSet();
		Set<ServiceBean> beanSet = annotationBeanConverter.convertToSet(clientBeanSet,
				ServiceBean.class);
		for (ServiceBean bean : beanSet) {
			Assert.assertEquals(ID, bean.getId());
			Assert.assertEquals(NAME, bean.getName());
			Assert.assertEquals(DIFFERNT_NAME, bean.getDifferentName1());
			Assert.assertEquals(LONG_VAR, bean.getLongVar());
			Assert.assertEquals(IGNORED_LONG_VAR_ZERO, bean.getIgnoredLongVar());
		}

	}

	/**
	 * Test converting a client bean containing collection objects 
	 * to a service bean containing collection objects.
	 */
	@Test
	public void testConvert_Collection() {
		BeanConverter annotationBeanConverter = new AnnotationBeanConverter();

		CollectionClientBean clientBean = CollectionBeanFactory.newCollectionClientBean();
		CollectionServiceBean serviceBean = annotationBeanConverter.convert(
				CollectionServiceBean.class, clientBean);

		ServiceBean bean1 = serviceBean.getServiceBean();
		Assert.assertEquals(ID, bean1.getId());
		Assert.assertEquals(NAME, bean1.getName());
		Assert.assertEquals(DIFFERNT_NAME, bean1.getDifferentName1());
		Assert.assertEquals(LONG_VAR, bean1.getLongVar());
		Assert.assertEquals(IGNORED_LONG_VAR_ZERO, bean1.getIgnoredLongVar());
		
		List<ServiceBean> beanList = serviceBean.getServiceBeanList();
		Assert.assertEquals(clientBean.getClientBeanList().size(), beanList.size());
		for(int i=0;i<beanList.size();++i) {
			ServiceBean bean = beanList.get(i);
			Assert.assertEquals(ID, bean.getId());
			Assert.assertEquals(NAME+i, bean.getName());
			Assert.assertEquals(DIFFERNT_NAME, bean.getDifferentName1());
			Assert.assertEquals(LONG_VAR, bean.getLongVar());
			Assert.assertEquals(IGNORED_LONG_VAR_ZERO, bean.getIgnoredLongVar());
		}

		Set<ServiceBean> beanSet = serviceBean.getServiceBeanSet();
		Assert.assertEquals(clientBean.getClientBeanSet().size(), beanSet.size());
		for (ServiceBean bean : beanSet) {
			Assert.assertEquals(ID, bean.getId());
			Assert.assertEquals(NAME, bean.getName());
			Assert.assertEquals(DIFFERNT_NAME, bean.getDifferentName1());
			Assert.assertEquals(LONG_VAR, bean.getLongVar());
			Assert.assertEquals(IGNORED_LONG_VAR_ZERO, bean.getIgnoredLongVar());
		}
		
		List<String> stringList = serviceBean.getStringList();
		Assert.assertEquals(NAME, stringList.get(0));
		Assert.assertEquals(DIFFERNT_NAME,stringList.get(1));
		
		int[] serviceIntArray = serviceBean.getServiceIntArray();
		for (int i = 0; i < serviceIntArray.length; i++) {
			int j = serviceIntArray[i];
			Assert.assertEquals(INT_ARRAY[i],j);
		}
	}

	@Test
	public void testConvert_SpringBeanUsage_Collection() {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"test-spring-context.xml"});
		BeanConverter annotationBeanConverter = (BeanConverter)context.getBean("xbean-convertor");
		
		
		CollectionClientBean clientBean = CollectionBeanFactory.newCollectionClientBean();
		CollectionServiceBean serviceBean = annotationBeanConverter.convert(
				CollectionServiceBean.class, clientBean);

		ServiceBean bean1 = serviceBean.getServiceBean();
		Assert.assertEquals(ID, bean1.getId());
		Assert.assertEquals(NAME, bean1.getName());
		Assert.assertEquals(DIFFERNT_NAME, bean1.getDifferentName1());
		Assert.assertEquals(LONG_VAR, bean1.getLongVar());
		Assert.assertEquals(IGNORED_LONG_VAR_ZERO, bean1.getIgnoredLongVar());
		
		List<ServiceBean> beanList = serviceBean.getServiceBeanList();
		Assert.assertEquals(clientBean.getClientBeanList().size(), beanList.size());
		for(int i=0;i<beanList.size();++i) {
			ServiceBean bean = beanList.get(i);
			Assert.assertEquals(ID, bean.getId());
			Assert.assertEquals(NAME+i, bean.getName());
			Assert.assertEquals(DIFFERNT_NAME, bean.getDifferentName1());
			Assert.assertEquals(LONG_VAR, bean.getLongVar());
			Assert.assertEquals(IGNORED_LONG_VAR_ZERO, bean.getIgnoredLongVar());
		}

		Set<ServiceBean> beanSet = serviceBean.getServiceBeanSet();
		Assert.assertEquals(clientBean.getClientBeanSet().size(), beanSet.size());
		for (ServiceBean bean : beanSet) {
			Assert.assertEquals(ID, bean.getId());
			Assert.assertEquals(NAME, bean.getName());
			Assert.assertEquals(DIFFERNT_NAME, bean.getDifferentName1());
			Assert.assertEquals(LONG_VAR, bean.getLongVar());
			Assert.assertEquals(IGNORED_LONG_VAR_ZERO, bean.getIgnoredLongVar());
		}
		
		List<String> stringList = serviceBean.getStringList();
		Assert.assertEquals(NAME, stringList.get(0));
		Assert.assertEquals(DIFFERNT_NAME,stringList.get(1));
		
		int[] serviceIntArray = serviceBean.getServiceIntArray();
		for (int i = 0; i < serviceIntArray.length; i++) {
			int j = serviceIntArray[i];
			Assert.assertEquals(INT_ARRAY[i],j);
		}
	}
	// TODO: Add tests for cases where you have same field names for different
	// types of members in different beans
	// TODO: Add tests for hierarchial classes/objects
}
