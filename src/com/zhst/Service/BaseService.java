package com.zhst.Service;

import com.zhst.Bean.BaseEntity;

public interface BaseService {

	/**
	    * 保存一个实体(增加)
	    * @param entity
	    * @throws Exception 
	    */ 
	   public <T extends BaseEntity>void save(T entity) throws Exception; 
	   /**
	    * 根据类型及id获得实体
	    * @param clazz
	    * @param id
	    * @return
	    * @throws Exception 
	    */
	   public <T extends BaseEntity> T get(Class<T> clazz, int id) throws Exception;
		    /**
	    * 更新实体
	    * @param entity
	    * @throws Exception 
	    */
	   public void update(BaseEntity entity) throws Exception;
	   /**
	    * 删除实体
	    * @param entity
	    * @throws Exception 
	    */
	   public void delete(BaseEntity entity) throws Exception; 
	   /**
	    * 保存或更新实体
	    * @param <T>
	    * @param entity
	    * @throws Exception 
	    */
	   <T extends BaseEntity> void saveOrUpdate(T entity) throws Exception;
	
}
