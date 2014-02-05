package com.mycompany.webapp.dao.impl;

import java.util.List;

import javax.persistence.Table;


import org.apache.cxf.common.util.StringUtils;
import org.appfuse.dao.GenericDao;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.webapp.dao.CFCUserDao;
import com.mycompany.webapp.model.CFCUser;
import com.mycompany.webapp.model.CFCUserType;

@Repository("cfcUserDao")
public class CFCUserDaoImpl extends GenericDaoHibernate<CFCUser, Long> implements CFCUserDao, UserDetailsService {

	 public CFCUserDaoImpl() {
	        super(CFCUser.class);
	    }

	 private CFCUserDao cfcUserDao;
	 
		
	

	/**
	     * {@inheritDoc}
	     */
	    @SuppressWarnings("unchecked")
	    public List<CFCUser> getUsers() {
	        Query qry = getSession().createQuery("from CFCUser u order by upper(u.username)");
	        return qry.list();
	    }

	    /**
	     * {@inheritDoc}
	     */
	    
	    public CFCUser saveUser(CFCUser user) {
	        if (log.isDebugEnabled()) {
	            log.debug("user's id: " + user.getId());
	        }
	       
	        getSession().saveOrUpdate(user);
	        // necessary to throw a DataIntegrityViolation and catch it in UserManager
	        getSession().flush();
	        return user;
	    }

	    /**
	     * Overridden simply to call the saveUser method. This is happening
	     * because saveUser flushes the session and saveObject of BaseDaoHibernate
	     * does not.
	     *
	     * @param user the user to save
	     * @return the modified user (with a primary key set if they're new)
	     */
	    @Override
	    @Transactional
	    public CFCUser save(CFCUser user) {
	        return this.saveUser(user);
	    }

	    /**
	     * {@inheritDoc}
	    */
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        List<CFCUser> users = getSession().createCriteria(CFCUser.class).add(Restrictions.eq("username", username)).list();
	        if (users == null || users.isEmpty()) {
	            throw new UsernameNotFoundException("user '" + username + "' not found...");
	        } else {
	            return users.get(0);
	        }
	    }

	    /**
	     * {@inheritDoc}
	    */
	    public String getUserPassword(Long userId) {
	        JdbcTemplate jdbcTemplate =
	                new JdbcTemplate(SessionFactoryUtils.getDataSource(getSessionFactory()));
	        Table table = AnnotationUtils.findAnnotation(CFCUser.class, Table.class);
	        return jdbcTemplate.queryForObject(
	                "select password from " + table.name() + " where id=?", String.class, userId);
	    }

		@Override
		public List<CFCUser> searchUser(CFCUser cfcUser) {
			// TODO Auto-generated method stub
			Criteria srchCriteria = getSession().createCriteria(CFCUser.class);
			System.out.println("userType -> "+cfcUser.getUserType());
			System.out.println("username -> "+cfcUser.getUsername());
			if (cfcUser.getUserType() != null)
				srchCriteria.add(Restrictions.eq("userType", cfcUser.getUserType()));
			if (!StringUtils.isEmpty(cfcUser.getUsername()))
				srchCriteria
						.add(Restrictions.eq("username", cfcUser.getUsername()));
	
			return srchCriteria.list();
			
		}
}
