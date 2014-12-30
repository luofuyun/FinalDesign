package com.zhst.Dao;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.zhst.Bean.BaseEntity;

/*
 * DAO 接口基类 
 */
public interface BaseDao {

	/*
	 * 根据Id获得实体、
	 * @param <T>
	 * @param id
	 * @return
	 */
	public <T extends BaseEntity> T get(Class<T> clas,int id) throws DataAccessException;
	
   /*
    * 保存实体
    * @param <T>
    * @param entity
    */
	public <T extends BaseEntity> void save(T entity) throws DataAccessException;
	
   /*
	* 更新实体
	* @param <T>
	* @param entity
	*/
	public <T extends BaseEntity> void update(T entity) throws DataAccessException;
	
	/*
	* 保存或更新实体
	* @param <T>
	* @param entity
	*/
	public <T extends BaseEntity> void saveOrupdate(T entity) throws DataAccessException;
	
	/*
	* 删除实体
	* @param <T>
	* @param entity
	*/
	public <T extends BaseEntity> void delete(T entity) throws DataAccessException;
	
	/*
	* 根据主键Id删除实体
	* @param <T>
	* @param entity
	* @param id
	*/
	public <T extends BaseEntity> void deleteById(Class<T> clas, int id) throws DataAccessException;
	
	/*
	* 使用  HQL语句直接增加，更新，删除实体
	* @param queryString
	* @return
	*/
	public int excute(String queryString) throws DataAccessException;
	
	/*
	* 使用带参数的  HQL语句直接增加，更新，删除实体
	* @param queryString
	* @param params
	* @return
	*/
	public int excute(String queryString,List<Object> params) throws DataAccessException;
	
	/*
	* 使用  HQL语句查询数据
	* @param queryString
	* @return List<T>
	*/
	public <T extends BaseEntity> List<T> find(String queryString) throws DataAccessException;
	
	/*
	* 使用带参数的  HQL语句查询数据
	* @param queryString
	* @param params
	* @return List<T>
	*/
	public <T extends BaseEntity> List<T> find(String queryString,List<Object> params) throws DataAccessException;
	
	/*
	* 根据属性值获得实体
	* @param <T>
	* @param clas
	* @param propertyName
	* @param value
	* @return
	*/
	public <T extends BaseEntity> List<T> findByProperty(Class<T> clas,String propertyName,Object value) throws DataAccessException;
	
	/*
	* 根据属性值判断实体是否存在
	* @param <T>
	* @param clas
	* @param propertyName
	* @param value
	* @return
	*/
	public <T extends BaseEntity> boolean isExist(Class<T> clas,String propertyName,Object value) throws DataAccessException;
	
	/*
	* 使用   HQL 分页查找
	* @param queryString
	* @param first
	* @param pageSize
	* @return
	*/
	public <T extends BaseEntity> List<T> find(String queryString,int first,int pageSize) throws DataAccessException;
	
	/*
	* 使用带参数的   HQL 分页查找
	* @param queryString
	* @param first
	* @param pageSize
    * @param params
	* @return
	*/
	public <T extends BaseEntity> List<T> find(String queryString,int first,int pageSize,List<Object> params) throws DataAccessException;
	
	/*
	* 使用HQL 获得数据总数
	* @param queryString
	* @return
	*/
	public int getTotalCount(String queryString) throws DataAccessException;
	
	/*
	* 使用带参数的  HQL获得数据总数
	* @param queryString
	* @param params
	* @return
	*/
	public int getTotalCount(String queryString,List<Object> params)throws DataAccessException;
	
	/*
     * 带参数的，自定义select列表的查询
     * @param queryString
     * @param params
     * @param first
     * @param pageSize
     * @return
     */
    public List findWithSelect(String queryString, List<Object> params) throws DataAccessException; 
   
    /*
     * 带参数的 ，自定义select列表分页查询
     * @param queryString
     * @param params
     * @param first
     * @param pageSize
     * @return
     */
    public List findWithSelect(String queryString, List<Object> params, int first, int pageSize) throws DataAccessException; 
}
