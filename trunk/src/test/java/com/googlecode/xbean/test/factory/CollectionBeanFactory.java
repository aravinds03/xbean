package com.xbean.test.factory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.xbean.test.bean.ClientBean;
import com.xbean.test.bean.collection.CollectionClientBean;

public class CollectionBeanFactory {

	public static CollectionClientBean newCollectionClientBean() {
		CollectionClientBean clientBean = new CollectionClientBean();
		clientBean.setClientBeanList(newClientBeanList());
		clientBean.setClientBeanSet(newClientBeanSet());
		clientBean.setClientBean(SingleBeanFactory.newClientBean());
		return clientBean;
	}

	public static List<ClientBean> newClientBeanList() {
		List<ClientBean> beanList = new ArrayList<ClientBean>();
		beanList.add(SingleBeanFactory.newClientBean());
		return beanList;
	}

	public static Set<ClientBean> newClientBeanSet() {
		Set<ClientBean> beanSet = new HashSet<ClientBean>();
		beanSet.add(SingleBeanFactory.newClientBean());
		return beanSet;
	}
}
