package com.zhst.Dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.zhst.Dao.ArrangeDao;
@Repository("ArrangeDao")
public class ArrangeDaoImpl extends BaseDaoImpl implements ArrangeDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
