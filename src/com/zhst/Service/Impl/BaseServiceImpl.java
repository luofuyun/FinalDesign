package com.zhst.Service.Impl;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zhst.Bean.BaseEntity;
import com.zhst.Dao.BaseDao;
import com.zhst.Service.BaseService;

@Service("baseService")
public class BaseServiceImpl implements BaseService,Serializable{

	protected Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
    @Qualifier("BaseDao")
    private BaseDao baseDao;
	
	@Override
	public <T extends BaseEntity>void save(T entity) throws Exception {
		log.info("BaseService:Save entity:"+entity.getClass().getSimpleName());
		baseDao.save(entity);
	}

	@Override
	public <T extends BaseEntity> T get(Class<T> clazz, int id) throws Exception {
		log.info("BaseService:Get entity:"+clazz.getSimpleName());
		return baseDao.get(clazz, id);
	}
	
	@Override
	public void update(BaseEntity entity) throws Exception {
		 log.info("BaseService:Update entity:"+entity.getClass().getSimpleName()+" with id="+entity.getId());
	        baseDao.update(entity);
	}

	@Override
	public void delete(BaseEntity entity) throws Exception {
		 log.info("BaseService:Delete entity:"+entity.getClass().getSimpleName()+" with id="+entity.getId());
	        baseDao.delete(entity);
	}

	@Override
	public <T extends BaseEntity> void saveOrUpdate(T entity) throws Exception {
		 log.info("BaseService:SaveOrUpdate entity:"+entity.getClass().getSimpleName()+" with id="+entity.getId());
	        baseDao.saveOrupdate(entity);
	}

}
