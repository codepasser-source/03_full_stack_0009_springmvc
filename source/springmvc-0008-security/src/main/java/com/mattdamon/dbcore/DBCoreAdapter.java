package com.mattdamon.dbcore;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public interface DBCoreAdapter {

	public abstract void setSessionFactory(SessionFactory sessionFactory);

	public abstract Session getSession();

	/**
	 * 根据ID获取实体对象
	 * 
	 * @param entityClass
	 * @param id
	 * @return T
	 */
	public abstract <T> T get(Class<T> entityClass, Serializable id);

	/**
	 * 根据ID数组获取实体对象的集合
	 * 
	 * @param entityClass
	 * @param ids
	 * @return List<T>
	 */
	public abstract <T> List<T> get(Class<T> entityClass, Object... ids);

	/**
	 * 根据ID数组获取实体对象的集合
	 * 
	 * @param entityClass
	 * @param ids
	 * @return List<T>
	 */
	public abstract <T> List<T> getAll(Class<T> entityClass);

	/**
	 * 根据属性和属性值获取获取实体对象集合
	 * 
	 * @param entityClass
	 * @param propertyName
	 * @param value
	 * @return List<T>
	 */
	public abstract <T> List<T> get(Class<T> entityClass, String propertyName,
			Object value);

	/**
	 * 根据ID获取实体对象
	 * 
	 * @param entityClass
	 * @param id
	 * @return T
	 */
	public abstract <T> T load(Class<T> entityClass, Serializable id);

	/**
	 * 通过hql查询语句查找HashMap对象
	 * 
	 * @param hql
	 *            参数以"?"占位
	 * @param objects
	 *            参数数组
	 * @return Map<Object, Object>
	 */
	public abstract Map<Object, Object> getMapByQuery(String hql,
			Object... param);

	/**
	 * 获取所有实体对象总数
	 * 
	 * @param entityClass
	 * @return Long
	 */
	@SuppressWarnings("rawtypes")
	public abstract Long getTotalCount(Class entityClass);

	/**
	 * 获取序列号
	 * 
	 * @param sequnceName
	 * @return Long
	 */
	public abstract Long getSequnce(String sequnceName);

	/**
	 * 通过hql查询唯一对象
	 * 
	 * @param hql
	 *            参数以"?"占位
	 * @param param
	 *            参数数组
	 * @return T
	 */
	public abstract <T> T singleResult(String hql, Object... param);

	/**
	 * 根据实体名字获取唯一记录
	 * 
	 * @param propertyName
	 * @param value
	 * @return T
	 */
	public abstract <T> T findUniqueByProperty(Class<T> entityClass,
			String propertyName, Object value);

	/**
	 * 按属性和属性值查找对象列表
	 * 
	 * @param propertyName
	 * @param value
	 * @return List<T>
	 */
	public abstract <T> List<T> findListByProperty(Class<T> entityClass,
			String propertyName, Object value);

	/**
	 * 按属性和属性值查找对象列表
	 * 
	 * @param propertyMap
	 *            查询条件(key:属性名,value:属性值)
	 * @param orderMap
	 *            排序条件（key:属性名,value:是否按升序排列）
	 * @return List<T>
	 */
	public abstract <T> List<T> findListByPropertyMap(Class<T> entityClass,
			Map<String, Object> propertyMap, Map<String, Boolean> orderMap);

	/**
	 * 按参数数组查找对象列表(hql)
	 * 
	 * @param hql
	 * @param param
	 * @return List<T>
	 */
	public abstract <T> List<T> findListByHql(String hql, Object... param);

	/**
	 * 根据hql取前几条数据(hql)
	 * 
	 * @param hql
	 * @param param
	 * @return List<T>
	 */
	public abstract <T> List<T> findListByHqlBef(String hql, int count,
			Object... param);

	/**
	 * 按参数数组查找对象列表(sql)
	 * 
	 * @param hql
	 * @param param
	 * @return List<T>
	 */
	public abstract <T> List<T> findListBySql(String sql, Object... param);

	/**
	 * 根据sql取前几条数据
	 * 
	 * @param <T>
	 * @param sql
	 * @param count
	 *            取得最大条数
	 * @param param
	 * @return
	 */
	public abstract <T> List<T> findListBySqlBef(String sql, int count,
			Object... param);

	/**
	 * 按属性和属性值排序查找对象列表
	 * 
	 * @param entityClass
	 * @param propertyName
	 * @param value
	 * @param isAsc
	 * @return List<T>
	 */
	public abstract <T> List<T> findByPropertyisOrder(Class<T> entityClass,
			String propertyName, Object value, boolean isAsc);

	/**
	 * 按模板查询对象列表
	 * 
	 * @param entityName
	 * @param exampleEntity
	 * @return List<T>
	 */
	@SuppressWarnings("rawtypes")
	public abstract List findListByExample(String entityName,
			Object exampleEntity);

	/**
	 * 根据传入的实体添加对象
	 * 
	 * @param entity
	 */
	public abstract <T> void save(T entity);

	/**
	 * 保存实体对象.
	 * 
	 * @param entity
	 *            对象
	 * @return ID
	 */
	public abstract <T, Pk> Pk saveReturnPk(T entity);

	/**
	 * 根据传入的实体添加或更新对象
	 * 
	 * @param entity
	 */
	public abstract <T> void saveOrUpdate(T entity);

	/**
	 * 批量保存数据
	 * 
	 * @param <T>
	 * @param entitys
	 *            要持久化的临时实体对象集合
	 */
	public abstract <T> void batchSave(List<T> entitys);

	/**
	 * 根据传入的实体添加对象
	 * 
	 * @param entity
	 */
	public abstract <T> void delete(T entity);

	/**
	 * 按ID删除实体
	 * 
	 * @param <T>
	 * 
	 * @param entitys
	 */
	public abstract <T> void deleteEntityById(Class<T> entityClass,
			Serializable id);

	/**
	 * 删除全部的实体
	 * 
	 * @param <T>
	 * 
	 * @param entitys
	 */
	public abstract <T> void deleteAllEntities(Collection<T> entitys);

	/**
	 * 更新指定的实体
	 * 
	 * @param <T>
	 * @param pojo
	 */
	public abstract <T> void update(T entity);

	/**
	 * 更新指定的实体
	 * 
	 * @param className
	 * @param obj
	 */
	public abstract void update(String className, Object obj);

	/**
	 * 更新指定的实体
	 * 
	 * @param entityName
	 * @param id
	 */
	@SuppressWarnings("rawtypes")
	public abstract void updateEntityById(Class entityName, Serializable id);

	/**
	 * 通过sql更新记录
	 * 
	 * @param sql
	 * @return
	 */
	public abstract int updateBySql(String sql, Object... param);

	/**
	 * 通过hql更新记录
	 * 
	 * @param hql
	 * @return
	 */
	public abstract int updateByHql(String hql, Object... param);

}