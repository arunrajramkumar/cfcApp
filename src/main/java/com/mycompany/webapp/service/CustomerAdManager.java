/**
 * 
 */
package com.mycompany.webapp.service;

import java.util.List;

import org.appfuse.service.GenericManager;
import org.appfuse.service.UserExistsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mycompany.webapp.model.CFCUser;
import com.mycompany.webapp.model.CustomerAd;

/**
 * Business Service Interface to handle communication between web and
 * persistence layer.
 *
 * 
 */public interface CustomerAdManager extends GenericManager<CustomerAd,Long>{

	 CustomerAd getId(String id);

	   
	  
	    List<CustomerAd> getAds();

	 
	    CustomerAd saveCustomerAd(CustomerAd user) throws Exception;

  }
