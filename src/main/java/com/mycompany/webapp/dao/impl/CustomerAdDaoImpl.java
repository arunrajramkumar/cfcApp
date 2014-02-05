/**
 * 
 */
package com.mycompany.webapp.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.mycompany.webapp.dao.CustomerAdDao;
import com.mycompany.webapp.model.CustomerAd;

/**
 * @author Administrator
 *
 */
@Repository("customerAdDao")
public class CustomerAdDaoImpl extends GenericDaoHibernate<CustomerAd, Long> implements CustomerAdDao {

	public CustomerAdDaoImpl() {
        super(CustomerAd.class);
    }
	
	public CustomerAdDaoImpl(Class<CustomerAd> persistentClass,
			SessionFactory sessionFactory) {
		super(persistentClass, sessionFactory);
		// TODO Auto-generated constructor stub
	}

	@Override
	public CustomerAd getId(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CustomerAd> getAds() {
		// TODO Auto-generated method stub
		  Query qry = getSession().createQuery("from CustomerAd u ");
	        return qry.list();
	}

	@Override
	public CustomerAd saveCustomerAd(CustomerAd custAd) throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
            log.debug("user's id: " + custAd.getId());
        }
       
        getSession().saveOrUpdate(custAd);
        // necessary to throw a DataIntegrityViolation and catch it in UserManager
        getSession().flush();
        return custAd;
	}

}
