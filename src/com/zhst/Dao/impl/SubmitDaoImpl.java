package com.zhst.Dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.zhst.Bean.Submit;
import com.zhst.Dao.SubmitDao;

@Repository("SubmitDao")
public class SubmitDaoImpl extends BaseDaoImpl implements SubmitDao {

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
