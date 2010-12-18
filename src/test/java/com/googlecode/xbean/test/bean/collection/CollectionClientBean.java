package com.googlecode.xbean.test.bean.collection;

import java.util.List;
import java.util.Set;

import com.googlecode.xbean.annotations.Ignore;
import com.googlecode.xbean.test.bean.ClientBean;

public class CollectionClientBean {

	private ClientBean clientBean;

	private List<ClientBean> clientBeanList;

	private Set<ClientBean> clientBeanSet;

	private List<String> stringList;

	private int[] clientIntArray;

	@Ignore
	private List<String> clientIgnoredStringList;

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

	public void setClientIgnoredStringList(List<String> clientIgnoredStringList) {
		this.clientIgnoredStringList = clientIgnoredStringList;
	}

	public List<String> getClientIgnoredStringList() {
		return clientIgnoredStringList;
	}

	public void setStringList(List<String> stringList) {
		this.stringList = stringList;
	}

	public List<String> getStringList() {
		return stringList;
	}

	public void setClientIntArray(int[] clientIntArray) {
		this.clientIntArray = clientIntArray;
	}

	public int[] getClientIntArray() {
		return clientIntArray;
	}

}
