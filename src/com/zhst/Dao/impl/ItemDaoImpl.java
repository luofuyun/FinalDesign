package com.zhst.Dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.jboss.logging.Param;
import org.springframework.stereotype.Repository;

import com.zhst.Bean.Item;
import com.zhst.Dao.ItemDao;


@Repository("ItemDao")
public class ItemDaoImpl extends BaseDaoImpl implements ItemDao{

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

	@Override
	public void save(Item file) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(file);
	}

	@Override
	public ArrayList<Item> getAllHomework() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 根据不同用户（管理员，教师）获得个人题库的数目
	 * 教师要加上课程条件进行筛选
	 */
	@Override
	public int getCountItemByUser(int userId, boolean isTeacher, int courseid) {
		List<Object> params = new ArrayList<Object>();
		StringBuffer queryString = new StringBuffer();
		queryString.append("from Item t  where t.creator.userId=?");
		params.add(userId);
		if(isTeacher){
		queryString.append(" and t.course.courseId=?");
		params.add(courseid);
		}
		queryString.append(" order by t.chapter,t.topic");
		return this.getTotalCount(queryString.toString(),params);
	}

	/**
	 * 根据不同用户（管理员，教师）获得个人题库
	 * 教师要加上课程条件进行筛选
	 */
	@Override
	public List<Item> getItemByUser(int start, int pagesize, int userId,
			boolean isTeacher, int courseid) {
		List<Object> params = new ArrayList<Object>();
		StringBuffer queryString = new StringBuffer();
		queryString.append("from Item where creator.userId=?");
		params.add(userId);
		if(isTeacher){
		queryString.append(" and course.courseId=?");
		params.add(courseid);
		}
		queryString.append(" order by chapter,topic");
		return this.find(queryString.toString(),start,pagesize,params);
	}

}
