/**
 * 
 */
package com.mycompany.webapp.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.mycompany.webapp.model.CustomerAd;

/**
 * @author Administrator
 *
 */
public interface CustomerAdDao extends GenericDao<CustomerAd, Long> {
      
	
	 CustomerAd getId(String id);

	   
	  
	    List<CustomerAd> getAds();

	 
	    CustomerAd saveCustomerAd(CustomerAd user) throws Exception;
}
