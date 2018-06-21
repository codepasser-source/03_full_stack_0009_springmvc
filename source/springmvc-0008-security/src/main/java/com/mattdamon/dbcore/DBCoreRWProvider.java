package com.mattdamon.dbcore;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@Repository
public class DBCoreRWProvider implements DBCoreAdapter {

	// -------------sessionFactory write-------------
	private SessionFactory sessionFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.neusoft.ecommerce.dbcore.DBCoreAdapter#setSessionFactory(org.hibernate
	 * .SessionFactory)
	 */
	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neusoft.ecommerce.dbcore.DBCoreAdapter#getSession()
	 */
	@Override
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	// -------------sessionFactory read-------------
	@Resource
	private SessionFactory sessionFactoryRead;

	@Resource(name = "sessionFactoryRead")
	public void setSessionFactoryRead(SessionFactory sessionFactoryRead) {
		this.sessionFactoryRead = sessionFactoryRead;
	}

	public Session getSessionRead() {
		return sessionFactoryRead.openSession();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neusoft.ecommerce.dbcore.DBCoreAdapter#get(java.lang.Class,
	 * java.io.Serializable)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T> T get(Class<T> entityClass, Serializable id) {
		Assert.notNull(id, "id is required");
		Session session = getSessionRead();
		T t = null;
		try {
			t = (T) session.get(entityClass, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
		return t;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neusoft.ecommerce.dbcore.DBCoreAdapter#get(java.lang.Class,
	 * java.lang.Object)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> get(Class<T> entityClass, Object... ids) {
		Assert.notEmpty(ids, "ids must not be empty");
		String hql = "from " + entityClass.getName()
				+ " as model where model.id in(:ids)";
		Session session = getSessionRead();
		List<T> list = null;
		try {
			list = session.createQuery(hql).setParameterList("ids", ids).list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neusoft.ecommerce.dbcore.DBCoreAdapter#getAll(java.lang.Class)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> getAll(Class<T> entityClass) {
		Session session = getSessionRead();
		String hql = "from " + entityClass.getName() + " as model";
		List<T> list = null;
		try {
			list = session.createQuery(hql).list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neusoft.ecommerce.dbcore.DBCoreAdapter#get(java.lang.Class,
	 * java.lang.String, java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> get(Class<T> entityClass, String propertyName,
			Object value) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Assert.notNull(value, "value is required");
		String hql = "from " + entityClass.getName() + " as model where model."
				+ propertyName + " = ?";
		Session session = getSessionRead();
		List<T> list = null;
		try {
			list = session.createQuery(hql).setParameter(0, value).list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neusoft.ecommerce.dbcore.DBCoreAdapter#load(java.lang.Class,
	 * java.io.Serializable)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T> T load(Class<T> entityClass, Serializable id) {
		Assert.notNull(id, "id is required");
		Session session = getSessionRead();
		T t = null;
		try {
			t = (T) session.load(entityClass, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
		return t;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.neusoft.ecommerce.dbcore.DBCoreAdapter#getMapByQuery(java.lang.String
	 * , java.lang.Object)
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public Map<Object, Object> getMapByQuery(String hql, Object... param) {

		Session session = getSessionRead();
		Map<Object, Object> map = null;
		try {
			Query query = session.createQuery(hql);
			if (param != null && param.length > 0) {
				for (int i = 0; i < param.length; i++) {
					query.setParameter(i, param[i]);
				}
			}
			List list = query.list();
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				if (map == null) {
					map = new HashMap<Object, Object>();
				}
				Object[] tm = (Object[]) iterator.next();
				map.put(tm[0].toString(), tm[1].toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
		return map;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.neusoft.ecommerce.dbcore.DBCoreAdapter#getTotalCount(java.lang.Class)
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public Long getTotalCount(Class entityClass) {
		String hql = "select count(*) from " + entityClass.getName();
		Session session = getSessionRead();
		Long count = 0L;
		try {
			count = (Long) session.createQuery(hql).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
		return count;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.neusoft.ecommerce.dbcore.DBCoreAdapter#getSequnce(java.lang.String)
	 */
	@Override
	public Long getSequnce(String sequnceName) {
		String sql = "select " + sequnceName + ".nextval as key from dual";
		Session session = getSessionRead();
		Long seq = -1L;
		try {
			seq = ((BigDecimal) session.createSQLQuery(sql).uniqueResult())
					.longValue();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
		return seq;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.neusoft.ecommerce.dbcore.DBCoreAdapter#singleResult(java.lang.String,
	 * java.lang.Object)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T> T singleResult(String hql, Object... param) {

		Session session = getSessionRead();
		T t = null;
		try {
			Query query = session.createQuery(hql);
			if (param != null && param.length > 0) {
				for (int i = 0; i < param.length; i++) {
					query.setParameter(i, param[i]);
				}
			}
			List<T> list = query.list();
			if (list.size() == 1) {
				// getSessionRead().flush();
				t = list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
		return t;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.neusoft.ecommerce.dbcore.DBCoreAdapter#findUniqueByProperty(java.
	 * lang.Class, java.lang.String, java.lang.Object)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T> T findUniqueByProperty(Class<T> entityClass,
			String propertyName, Object value) {

		Session session = getSessionRead();
		T t = null;
		try {
			t = (T) createCriteria(session, entityClass,
					Restrictions.eq(propertyName, value)).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
		return t;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.neusoft.ecommerce.dbcore.DBCoreAdapter#findListByProperty(java.lang
	 * .Class, java.lang.String, java.lang.Object)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> findListByProperty(Class<T> entityClass,
			String propertyName, Object value) {
		Session session = getSessionRead();
		List<T> list = null;
		try {
			list = (List<T>) createCriteria(session, entityClass,
					Restrictions.eq(propertyName, value)).list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.neusoft.ecommerce.dbcore.DBCoreAdapter#findListByPropertyMap(java
	 * .lang.Class, java.util.Map, java.util.Map)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> findListByPropertyMap(Class<T> entityClass,
			Map<String, Object> propertyMap, Map<String, Boolean> orderMap) {
		Assert.notNull(propertyMap, "property must not be null");

		Session session = getSessionRead();
		List<T> list = null;
		try {
			list = (List<T>) createCriteria(session, entityClass, orderMap,
					Restrictions.allEq(propertyMap)).list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}

		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.neusoft.ecommerce.dbcore.DBCoreAdapter#findListByHql(java.lang.String
	 * , java.lang.Object)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> findListByHql(String hql, Object... param) {
		Session session = getSessionRead();
		List<T> list = null;
		try {
			Query query = session.createQuery(hql);
			for (int i = 0; i < param.length; i++) {
				query.setParameter(i, param[i]);
			}
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.neusoft.ecommerce.dbcore.DBCoreAdapter#findListByHqlBef(java.lang
	 * .String, int, java.lang.Object)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> findListByHqlBef(String hql, int count, Object... param) {
		Session session = getSessionRead();
		List<T> list = null;
		try {
			Query query = session.createQuery(hql);

			for (int i = 0; i < param.length; i++) {
				query.setParameter(i, param[i]);
			}
			if (count > 0) {
				query.setMaxResults(count);
			}
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.neusoft.ecommerce.dbcore.DBCoreAdapter#findListBySql(java.lang.String
	 * , java.lang.Object)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> findListBySql(String sql, Object... param) {
		Session session = getSessionRead();
		List<T> list = null;
		try {
			Query query = session.createSQLQuery(sql);
			for (int i = 0; i < param.length; i++) {
				query.setParameter(i, param[i]);
			}
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.neusoft.ecommerce.dbcore.DBCoreAdapter#findListBySqlBef(java.lang
	 * .String, int, java.lang.Object)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> findListBySqlBef(String sql, int count, Object... param) {
		Session session = getSessionRead();
		List<T> list = null;
		try {
			Query query = session.createSQLQuery(sql);
			for (int i = 0; i < param.length; i++) {
				query.setParameter(i, param[i]);
			}
			if (count > 0) {
				query.setMaxResults(count);
			}
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.neusoft.ecommerce.dbcore.DBCoreAdapter#findByPropertyisOrder(java
	 * .lang.Class, java.lang.String, java.lang.Object, boolean)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> findByPropertyisOrder(Class<T> entityClass,
			String propertyName, Object value, boolean isAsc) {

		Session session = getSessionRead();
		List<T> list = null;
		try {
			list = createCriteria(session, entityClass, isAsc,
					Restrictions.eq(propertyName, value)).list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}

		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.neusoft.ecommerce.dbcore.DBCoreAdapter#findListByExample(java.lang
	 * .String, java.lang.Object)
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List findListByExample(String entityName, Object exampleEntity) {
		Assert.notNull(exampleEntity, "Example entity must not be null");
		Session session = getSessionRead();
		List list = null;
		try {
			Criteria criteria = (entityName != null ? session
					.createCriteria(entityName) : session
					.createCriteria(exampleEntity.getClass()));
			criteria.add(Example.create(exampleEntity));
			list = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.flush();
				session.close();
			}
		}
		return list;
	}

	private <T> Criteria createCriteria(Session session, Class<T> entityClass,
			Criterion... criterions) {
		Criteria criteria = session.createCriteria(entityClass);
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}

	private <T> Criteria createCriteria(Session session, Class<T> entityClass,
			boolean isAsc, Criterion... criterions) {
		Criteria criteria = createCriteria(session, entityClass, criterions);
		if (isAsc) {
			criteria.addOrder(Order.asc("asc"));
		} else {
			criteria.addOrder(Order.desc("desc"));
		}
		return criteria;
	}

	private <T> Criteria createCriteria(Session session, Class<T> entityClass,
			Map<String, Boolean> orderMap, Criterion... criterions) {
		Criteria criteria = createCriteria(session, entityClass, criterions);
		if (orderMap != null) {
			for (Entry<String, Boolean> entry : orderMap.entrySet()) {
				if (entry.getValue()) {
					criteria.addOrder(Order.asc(entry.getKey()));
				} else {
					criteria.addOrder(Order.desc(entry.getKey()));
				}
			}
		}
		return criteria;
	}

	/***** write *****/

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neusoft.ecommerce.dbcore.DBCoreAdapter#save(T)
	 */
	@Override
	public <T> void save(T entity) {
		getSession().save(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neusoft.ecommerce.dbcore.DBCoreAdapter#saveReturnPk(T)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T, Pk> Pk saveReturnPk(T entity) {
		Assert.notNull(entity, "entity不能为空");
		return (Pk) getSession().save(entity);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neusoft.ecommerce.dbcore.DBCoreAdapter#saveOrUpdate(T)
	 */
	@Override
	public <T> void saveOrUpdate(T entity) {
		getSession().saveOrUpdate(entity);
		// getSessionWrite().flush();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neusoft.ecommerce.dbcore.DBCoreAdapter#batchSave(java.util.List)
	 */
	@Override
	public <T> void batchSave(List<T> entitys) {
		for (int i = 0; i < entitys.size(); i++) {
			getSession().save(entitys.get(i));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neusoft.ecommerce.dbcore.DBCoreAdapter#delete(T)
	 */
	@Override
	public <T> void delete(T entity) {
		Assert.notNull(entity, "entity不能为空");
		getSession().delete(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.neusoft.ecommerce.dbcore.DBCoreAdapter#deleteEntityById(java.lang
	 * .Class, java.io.Serializable)
	 */
	@Override
	public <T> void deleteEntityById(Class<T> entityClass, Serializable id) {
		delete(get(entityClass, id));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.neusoft.ecommerce.dbcore.DBCoreAdapter#deleteAllEntities(java.util
	 * .Collection)
	 */
	@Override
	public <T> void deleteAllEntities(Collection<T> entitys) {
		for (Object entity : entitys) {
			getSession().delete(entity);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neusoft.ecommerce.dbcore.DBCoreAdapter#update(T)
	 */
	@Override
	public <T> void update(T entity) {
		getSession().update(entity);
		// getSessionWrite().flush();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neusoft.ecommerce.dbcore.DBCoreAdapter#update(java.lang.String,
	 * java.lang.Object)
	 */
	@Override
	public void update(String className, Object obj) {
		getSession().update(className, obj);
		// getSessionWrite().flush();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.neusoft.ecommerce.dbcore.DBCoreAdapter#updateEntityById(java.lang
	 * .Class, java.io.Serializable)
	 */
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void updateEntityById(Class entityName, Serializable id) {
		update(get(entityName, id));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.neusoft.ecommerce.dbcore.DBCoreAdapter#updateBySql(java.lang.String,
	 * java.lang.Object)
	 */
	@Override
	public int updateBySql(String sql, Object... param) {
		Query query = getSession().createSQLQuery(sql);
		for (int i = 0; i < param.length; i++) {
			query.setParameter(i, param[i]);
		}
		return query.executeUpdate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.neusoft.ecommerce.dbcore.DBCoreAdapter#updateByHql(java.lang.String,
	 * java.lang.Object)
	 */
	@Override
	public int updateByHql(String hql, Object... param) {
		Query query = getSession().createQuery(hql);
		for (int i = 0; i < param.length; i++) {
			query.setParameter(i, param[i]);
		}
		return query.executeUpdate();
	}
}
