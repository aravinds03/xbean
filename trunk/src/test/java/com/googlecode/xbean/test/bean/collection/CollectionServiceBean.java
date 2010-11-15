package com.googlecode.xbean.test.bean.collection;

import java.util.List;
import java.util.Set;

import com.googlecode.xbean.annotations.Convertible;
import com.googlecode.xbean.test.bean.ServiceBean;

public class CollectionServiceBean {

	@Convertible(value = "clientBean", auto = true)
	private ServiceBean serviceBean;

	@Convertible("clientBeanList")
	private List<ServiceBean> serviceBeanList;

	@Convertible("clientBeanSet")
	private Set<ServiceBean> serviceBeanSet;

	/**
	 * @return the serviceBean
	 */
	public ServiceBean getServiceBean() {
		return serviceBean;
	}

	/**
	 * @param pServiceBean
	 *            the serviceBean to set
	 */
	public void setServiceBean(ServiceBean pServiceBean) {
		serviceBean = pServiceBean;
	}

	/**
	 * @return the serviceBeanList
	 */
	public List<ServiceBean> getServiceBeanList() {
		return serviceBeanList;
	}

	/**
	 * @param pServiceBeanList
	 *            the serviceBeanList to set
	 */
	public void setServiceBeanList(List<ServiceBean> pServiceBeanList) {
		serviceBeanList = pServiceBeanList;
	}

	/**
	 * @return the serviceBeanSet
	 */
	public Set<ServiceBean> getServiceBeanSet() {
		return serviceBeanSet;
	}

	/**
	 * @param pServiceBeanSet
	 *            the serviceBeanSet to set
	 */
	public void setServiceBeanSet(Set<ServiceBean> pServiceBeanSet) {
		serviceBeanSet = pServiceBeanSet;
	}

}
