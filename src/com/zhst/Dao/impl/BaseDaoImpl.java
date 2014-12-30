package com.zhst.Dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.zhst.Bean.BaseEntity;
import com.zhst.Dao.BaseDao;
/*
 * DAO 基类实现类
 */
@Repository("BaseDao")
public class BaseDaoImpl implements BaseDao ,Serializable{

	protected Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
	
	public void flush() {
        getSession().flush();
    }
	
	@Override
	public <T extends BaseEntity> T get(Class<T> clas, int id) throws DataAccessException {
		log.info("DAO:get entity "+clas.getSimpleName()+" : Id = "+ id);	
		return (T) getSession().get(clas, id);
	}

	@Override
	public <T extends BaseEntity> void save(T entity) throws DataAccessException {
	    log.info("DAO:save entity "+entity.getClass().getSimpleName());
	    getSession().save(entity);
	}

	@Override
	public <T extends BaseEntity> void update(T entity) throws DataAccessException {
		log.info("DAO:update entity "+entity.getClass().getSimpleName()+"Id = "+entity.getId());
		getSession().beginTransaction();
		getSession().update(entity);
		getSession().getTransaction().commit();
		getSession().flush();
	}

	@Override
	public <T extends BaseEntity> void saveOrupdate(T entity) throws DataAccessException {
		log.info("DAO:saveOrupdate entity "+entity.getClass().getSimpleName()+"Id = "+entity.getId());
		getSession().beginTransaction();
		getSession().saveOrUpdate(entity);
		getSession().getTransaction().commit();
		getSession().flush();
		
	}

	@Override
	public <T extends BaseEntity> void delete(T entity) throws DataAccessException {
		log.info("DAO:delete entity "+ entity.getClass().getSimpleName()+"Id = "+entity.getId());
		getSession().beginTransaction();
		getSession().delete(entity);
		getSession().getTransaction().commit();
		getSession().flush();
	}

	@Override
	public <T extends BaseEntity> void deleteById(Class<T> clas, int id)
			throws DataAccessException {
       log.info("DAO:delete entity by Id "+clas.getSimpleName()+"Id = "+id);
	   this.delete((T)getSession().load(clas, id));
	}

	@Override
	public int excute(String queryString) throws DataAccessException {
		log.info("DAO:Excute HQL "+queryString);
		Query query = getSession().createQuery(queryString);
		return query.executeUpdate();
	}

	@Override
	public int excute(String queryString, List<Object> params)
			throws DataAccessException {
		log.info("DAO:Excute HQL With params:"+queryString);
        Query query = getSession().createQuery(queryString);
        for (int i = 0; i < params.size(); i++) {
            log.info("DAO:set param:"+ params.get(i));
            query.setParameter(i, params.get(i));
        }
		return query.executeUpdate();
	}

	@Override
	public <T extends BaseEntity> List<T> find(String queryString) throws DataAccessException {
        log.info("DAO:Query HQL "+queryString);
        Query query = getSession().createQuery(queryString);
        query.setCacheable(true);//使用缓存
		return query.list();
	}

	@Override
	public <T extends BaseEntity> List<T> find(String queryString, List<Object> params)
			throws DataAccessException {
		log.info("DAO:Query HQL With params "+queryString);
		Query query = getSession().createQuery(queryString);
		for(int i = 0;i<params.size();i++){
			log.info("DAO:set param "+params.get(i));
			query.setParameter(i, params.get(i));
		}
		return query.list();
	}

	/**
	 * (non-Javadoc)
	 * @see com.zhst.Dao.BaseDao#findByProperty(java.lang.Class, java.lang.String, java.lang.Object)
	 *
	 */
	@Override
	public <T extends BaseEntity> List<T> findByProperty(Class<T> clas, String propertyName, Object value)
			throws DataAccessException {
		log.info("DAO:Query "+clas.getSimpleName()+"With propertyName "+propertyName);
		String queryString = "from "+clas.getSimpleName()+" as A where A."+propertyName+"='"+value+"'";
		return getSession().createQuery(queryString).list();
	}

	@Override
	public <T extends BaseEntity> boolean isExist(Class<T> clas, String propertyName, Object value)
			throws DataAccessException {
		 log.info("DAO:Query If exist entity "+clas.getSimpleName()+" by :"+propertyName);
	        List<T> t = findByProperty(clas, propertyName, value);
	        return (t.size() != 0);
	}

	@Override
	public <T extends BaseEntity> List<T> find(String queryString, int first, int pageSize)
			throws DataAccessException {
		  log.info("DAO:Query HQL by page :"+queryString);
	        Query query = getSession().createQuery(queryString);
	        query.setFirstResult(first).setMaxResults(pageSize);//使用hibernate的分页查询
	        query.setCacheable(true);
	        return query.list();
	}

	@Override
	public <T extends BaseEntity> List<T> find(String queryString, int first, int pageSize,
			List<Object> params) throws DataAccessException {
		 log.info("DAO:Query HQL with params by page :"+queryString);
	        Query query = getSession().createQuery(queryString);
	        for (int i = 0; i < params.size(); i++) {
	            log.info("DAO:set param:"+ params.get(i));
	            query.setParameter(i, params.get(i));
	        }
	        query.setFirstResult(first).setMaxResults(pageSize);
	        query.setCacheable(true);
	        return query.list();
	}

	@Override
	public int getTotalCount(String queryString) throws DataAccessException {
		 log.info("DAO:Query HQL  for Total Count of data :"+queryString);
	        queryString = "select count(t.id) " + queryString;
	        Query query = getSession().createQuery(queryString);
	        query.setCacheable(true);
	        return ((Long) query.uniqueResult()).intValue();
	}

	@Override
	public int getTotalCount(String queryString, List<Object> params)
			throws DataAccessException {
		 log.info("DAO:Query HQL  for Total Count of data with params :"+queryString);
	        queryString = "select count(t.id) "+queryString;
	        Query query = getSession().createQuery(queryString);      
	        for (int i = 0; i < params.size(); i++) {
	            log.info("DAO:set param:"+ params.get(i));
	            query.setParameter(i, params.get(i));
	        }
	        query.setCacheable(true);
	        return ((Long) query.uniqueResult()).intValue();
	}

	@Override
	public List findWithSelect(String queryString, List<Object> params)
			throws DataAccessException {
		 log.info("DAO:Query HQL:"+queryString);	   
	        Query query = getSession().createQuery(queryString);
	        for (int i = 0; i < params.size(); i++) {
	            log.info("DAO:set param:"+ params.get(i));
	            query.setParameter(i, params.get(i));
	        }
	        query.setCacheable(true);
	        return query.list();
	}

	@Override
	public List findWithSelect(String queryString, List<Object> params,
			int first, int pageSize) throws DataAccessException {
		 log.info("DAO:Query HQL with params by page :"+queryString);
	        Query query = getSession().createQuery(queryString);
	        for (int i = 0; i < params.size(); i++) {
	            log.info("DAO:set param:"+ params.get(i));
	            query.setParameter(i, params.get(i));
	        }
	        query.setFirstResult(first).setMaxResults(pageSize);
	        query.setCacheable(true);
	        return query.list();
	}

}
