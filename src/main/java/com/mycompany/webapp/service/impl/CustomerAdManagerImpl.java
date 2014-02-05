package com.mycompany.webapp.service.impl;

import java.util.List;

import org.appfuse.service.impl.GenericManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.CFCUserDao;
import com.mycompany.webapp.dao.CustomerAdDao;
import com.mycompany.webapp.model.CustomerAd;
import com.mycompany.webapp.service.CustomerAdManager;

@Service("customerAdManager")
public class CustomerAdManagerImpl extends GenericManagerImpl<CustomerAd, Long> implements CustomerAdManager {

	 @Autowired
	 private CustomerAdDao customerAdDao;
	
	
	@Override
	public CustomerAd save(CustomerAd object) {
		// TODO Auto-generated method stub
		return customerAdDao.save(object);
	}

	@Override
	public void remove(CustomerAd object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CustomerAd getId(String id) {
		// TODO Auto-generated method stub
		return customerAdDao.getId(id);
	}

	@Override
	public List<CustomerAd> getAds() {
		// TODO Auto-generated method stub
		return customerAdDao.getAds();
	}

	@Override
	public CustomerAd saveCustomerAd(CustomerAd user) throws Exception {
		// TODO Auto-generated method stub
		return customerAdDao.save(user);
	}

}
