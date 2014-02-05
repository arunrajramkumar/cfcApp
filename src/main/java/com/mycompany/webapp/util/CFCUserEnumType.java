package com.mycompany.webapp.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;

import com.mycompany.webapp.model.CFCUserType;

public class CFCUserEnumType {// extends PersistentEnumUserType<CFCUserType>{

	/* @Override
	    public Class<CFCUserType> returnedClass() {
	        return CFCUserType.class;
	    }

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names,
			SessionImplementor session, Object owner)
			throws HibernateException, SQLException {
		// TODO Auto-generated method stub
		return super.nullSafeGet(rs, names, owner);
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index,
			SessionImplementor session) throws HibernateException, SQLException {
		// TODO Auto-generated method stub
		super.nullSafeSet(st, value, index);
	}*/

}
