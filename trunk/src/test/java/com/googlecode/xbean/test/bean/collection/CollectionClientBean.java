package com.xbean.test.bean.collection;

import java.util.List;
import java.util.Set;

import com.xbean.test.bean.ClientBean;

public class CollectionClientBean {

	private ClientBean clientBean;

	private List<ClientBean> clientBeanList;

	private Set<ClientBean> clientBeanSet;

	/**
	 * @return the clientBean
	 */
	public ClientBean getClientBean() {
		return clientBean;
	}

	/**
	 * @param pClientBean
	 *            the clientBean to set
	 */
	public void setClientBean(ClientBean pClientBean) {
		clientBean = pClientBean;
	}

	/**
	 * @return the clientBeanList
	 */
	public List<ClientBean> getClientBeanList() {
		return clientBeanList;
	}

	/**
	 * @param pClientBeanList
	 *            the clientBeanList to set
	 */
	public void setClientBeanList(List<ClientBean> pClientBeanList) {
		clientBeanList = pClientBeanList;
	}

	/**
	 * @return the clientBeanSet
	 */
	public Set<ClientBean> getClientBeanSet() {
		return clientBeanSet;
	}

	/**
	 * @param pClientBeanSet
	 *            the clientBeanSet to set
	 */
	public void setClientBeanSet(Set<ClientBean> pClientBeanSet) {
		clientBeanSet = pClientBeanSet;
	}

}
