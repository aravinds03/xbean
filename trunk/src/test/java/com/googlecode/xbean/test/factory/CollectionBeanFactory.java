package com.googlecode.xbean.test.factory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.googlecode.xbean.test.bean.ClientBean;
import com.googlecode.xbean.test.bean.collection.CollectionClientBean;

public class CollectionBeanFactory {

	public static CollectionClientBean newCollectionClientBean() {
		CollectionClientBean clientBean = new CollectionClientBean();
		clientBean.setClientBeanList(newClientBeanList());
		clientBean.setClientBeanSet(newClientBeanSet());
		clientBean.setClientBean(SingleBeanFactory.newClientBean());
		ArrayList<String> stringList = new ArrayList<String>();
		clientBean.setStringList(stringList);
		
		stringList.add(FactoryConstants.NAME); 
		stringList.add(FactoryConstants.DIFFERNT_NAME);
		
		clientBean.setClientIntArray(FactoryConstants.INT_ARRAY);
		
		 
		return clientBean;
	}

	public static List<ClientBean> newClientBeanList() {
		List<ClientBean> beanList = new ArrayList<ClientBean>();
		for (int i = 0; i < 5; ++i) {
			ClientBean newClientBean = SingleBeanFactory.newClientBean();
			newClientBean.setName(newClientBean.getName() + i);
			beanList.add(newClientBean);
		}

		return beanList;
	}

	public static Set<ClientBean> newClientBeanSet() {
		Set<ClientBean> beanSet = new HashSet<ClientBean>();
		for (int i = 0; i < 5; ++i) {
			beanSet.add(SingleBeanFactory.newClientBean());
		}
		return beanSet;
	}
}
