package com.zhst.Dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.zhst.Bean.Document;
import com.zhst.Dao.DocumentDao;

@Repository("DocumentDao")
public class DocumentDaoImpl extends BaseDaoImpl implements DocumentDao {

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
