/**============================================================
 * 修改记录：
 * 日期                           	作者                            内容
 * =============================================================
 * 2012-06-16     zhaobai       创建文件，实现基本功能
 * ============================================================*/
package com.kelvem.common.base;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kelvem.common.model.PageResults;

/**
 * <p>所有Dao的基类</p>
 * 
 * <p>Copyright: 版权所有 (c) 2012 - 2014</p>
 * <p>Company: kelvem</p>
 * 
 * @ClassName HibernateDaoBase
 * @author kelvem
 * @version 1.0
 */
public class Hibernate3DaoBase<T extends Serializable,PK extends Serializable> extends HibernateDaoSupport {
	/**
	 * Log4J Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(Hibernate3DaoBase.class);
	
	
	/**
	 * <p>根据序列名称获取序列值</p>
	 * 
	 * @param seqName
	 * @return Integer
	 */
	public Long getSequenceNextvalBySeqName(String seqName){
		StringBuffer sql = new StringBuffer("Select ")
									.append(seqName)
									.append(".nextval ")
									.append("from dual");
		java.math.BigDecimal id = (java.math.BigDecimal)this.getSession().createSQLQuery(sql.toString()).uniqueResult();
		return id.longValue();
	}

	/**
	 * <p>保存实体对象</p>
	 * 
	 * @param object
	 * @return 单主键Long类型
	 * @see 其他相关
	 */
	public Serializable save(T object) {
		return super.getHibernateTemplate().save(object);
	}

	/**
	 * <p>保存或更新实体对象</p>
	 * 
	 * @param object 实体T
	 * @see public Long save(T object)
	 */
	public void saveOrUpdate(T object) {
		super.getHibernateTemplate().saveOrUpdate(object);
	}
	
	/**
	 * <p>保存实体对象</p>
	 * 
	 * @param objectlist 实体对象列表
	 * @return int 实体数
	 */
	public int save(List<T> objectlist) {
		for (int i = 0; i < objectlist.size(); i++) {
			super.getHibernateTemplate().save(objectlist.get(i));
			if (i % 30 == 0) {
				this.getHibernateTemplate().flush();
				this.getHibernateTemplate().clear();
			}
		}
		return objectlist.size();
	}

	/**
	 * <p>删除实体</p>
	 * 
	 * @param object 实体
	 */
	public void delete(T object) {
		this.getHibernateTemplate().delete(object);
	}
	
	/**
	 * <p>删除实体对象</p>
	 * 
	 * @param objectlist 实体对象列表
	 * @return int 实体数
	 */
	public int delete(List<T> objectlist) {
		for (int i = 0; i < objectlist.size(); i++) {
			super.getHibernateTemplate().delete(objectlist.get(i));
			if (i % 30 == 0) {
				this.getHibernateTemplate().flush();
				this.getHibernateTemplate().clear();
			}
		}
		return objectlist.size();
	}

	/**
	 * <p>更新实体</p>
	 * 
	 * @param object 实体
	 */
	public void update(T object) {
		this.getHibernateTemplate().update(object);
	}
	
	/**
	 * <p>根据实体对象查询实体列表</p>
	 * 
	 * @param object 实体对象
	 * @return List<T> 满足查询条件的实体列表
	 * @see 其他相关
	 */
	public List<T> findByExample(final T object) {
		List<T> list = (List<T>) this.getHibernateTemplate().execute(
				new HibernateCallback<List<T>>() {
					@SuppressWarnings("unchecked")
					public List<T> doInHibernate(Session session)
							throws HibernateException {
						return (List<T>) session.createCriteria(object.getClass()).add(
								Example.create(object)).list();
					}
				});
		return list;
	}

	/**
	 * <p>根据实体属性查询实体列表</p>
	 * 
	 * @param className 实体类
	 * @param propertyName 属性名
	 * @param value 属性值班
	 * @return  List<T> 满足查询条件的实体列表
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByProperty(Class<T> className, String propertyName,
			Object value) {
		String querysql = "from " + className.getName()
				+ " as model where model." + propertyName + "=?";
		return super.getHibernateTemplate().find(querysql, value);
	}

	/**
	 * <p>根据实体ID查询单个的实体对象</p>
	 * 
	 * @param className
	 * @param id
	 * @return T 实体
	 * @see public List<T> findByLikedPropertys(final Class<T> className,final String[] props, final Object[] vals)
	 */
	public T  getById(Class<T> className, PK id) {
		return (T)this.getHibernateTemplate().get(className, id);
	}

	/**
	 * <p>根据实体属性列表及值模糊查询实体列表</p>
	 * 
	 * @param className 实体类
	 * @param props[] 属性数据
	 * @param vals[] 属性值
	 * @return List<T> 满足查询条件的实体列表
	 * @see public List<T> findByProperty(Class<T> className, String propertyName,Object value)
	 */
	public List<T> findByLikedPropertys(final Class<T> className,
			final String[] props, final Object[] vals) {
		if (logger.isDebugEnabled()) {
			logger
					.debug("findByLikedPropertys(Class, String[], Object[]) - start");
		}

		List<T> list = (List<T>) this.getHibernateTemplate().execute(
				new HibernateCallback<List<T>>() {
					@SuppressWarnings("unchecked")
					public List<T> doInHibernate(Session session)
							throws HibernateException, SQLException {
						if (logger.isDebugEnabled()) {
							logger.debug("doInHibernate(Session) - start");
						}

						List<T> l = null;

						Criteria criteria = session.createCriteria(className);
						for (int i = 0; i < props.length; i++) {
							if (vals[i] != null) {
								criteria.add(Restrictions.like(props[i], "%"
										+ vals[i] + "%"));

							}
						}
						l = criteria.list();

						if (logger.isDebugEnabled()) {
							logger.debug("return result size is =" + l.size());
							logger.debug("doInHibernate(Session) - end");
						}
						return l;
					}

				});
		if (logger.isDebugEnabled()) {
			logger
					.debug("findByLikedPropertys(Class, String[], Object[]) - end");
		}
		return list;
	}

	/**
	 * <p>根据实体对象查询实体列表</p>
	 * 
	 * @param example
	 * @param orderPropertyName 排序属性DESC
	 * @return List<T>
	 */
	public List<T> findByExampleForOrder(final T example,final String orderPropertyName) {
		List<T> l = (List<T>) this.getHibernateTemplate().execute(
				new HibernateCallback<List<T>>() {
					@SuppressWarnings("unchecked")
					public List<T> doInHibernate(Session session)
							throws HibernateException {
						return session
								.createCriteria(example.getClass())
								.add(Example.create(example))
//								.add(Restrictions.eq("tblquestion", inobject))
								.addOrder(Property.forName(orderPropertyName).desc())
								.list();

					}
				});
		return l;

	}
	
	/**
	 * <p>查询实体数</p>
	 * 
	 * @param t 实体
	 * @return Integer 实体数
	 */
	protected Integer CountRowByExample(final T t) {
		return getHibernateTemplate().execute(
				new HibernateCallback<Integer>() {
					public Integer doInHibernate(Session session)
							throws HibernateException {
						Criteria criteria = session.createCriteria(
								t.getClass()).add(Example.create(t));
						Integer totalCountObject = (Integer) criteria
								.setProjection(Projections.rowCount())
								.uniqueResult();
						return totalCountObject;
					}
				});
	}
	
	/**
	 * <p>根据HQL分页查询</p>
	 * 
	 * @param hql 查询当前页的HQL
	 * @param countHql 查询总数的HQL
	 * @param pageSize 每页大小
	 * @param pageNo 当前页号
	 * @return
	 * @see
	 */
	@SuppressWarnings("unchecked")
	protected PageResults<T> findPageByHQL(String hql,String countHql, final int pageSize,final int pageNo){
		Long totalCountObject = (Long)this.getSession().createQuery(countHql.toString()).uniqueResult();
		int totalCount = (totalCountObject == null) ? 0 : totalCountObject.intValue();

		PageResults<T> retValue = new PageResults<T>();
		retValue.setCurrentPage(pageNo);
		retValue.setPageSize(pageSize);
		retValue.setTotalCount(totalCount);
		List<T> items = this.getSession().createQuery(hql)
										.setFirstResult(retValue.getResultsFrom())
										.setMaxResults(pageSize)
										.list();
		retValue.setResults(items);
		return retValue;
	}
	
	/**
	 * <p>分页查询</p>
	 * 
	 * @param detachedCriteria
	 * @param pageSize 页大小
	 * @param pageNo  当前页号
	 * @return PageResults<T>
	 */
	protected PageResults<T> findPageByCriteria(
			final DetachedCriteria detachedCriteria, final int pageSize,
			final int pageNo) {
		return (PageResults<T>) getHibernateTemplate().execute(
				new HibernateCallback<PageResults<T>>() {
					@SuppressWarnings("unchecked")
					public PageResults<T> doInHibernate(Session session)
							throws HibernateException {
						Criteria criteria = detachedCriteria
								.getExecutableCriteria(session);
						Long totalCountObject = (Long) criteria
								.setProjection(Projections.rowCount())
								.uniqueResult();
						int totalCount = (totalCountObject == null) ? 0 : totalCountObject.intValue();

						PageResults<T> retValue = new PageResults<T>();
						retValue.setCurrentPage(pageNo);
						retValue.setPageSize(pageSize);
						retValue.setTotalCount(totalCount);

						criteria.setProjection(null);
						
						/* 取消此段无意义代码  by wanglinzi
						 * List
						 ids=criteria.setProjection(Projections.distinct(Projections.id())).list();
						 List ids =
						 criteria.setProjection(Projections.distinct(Projections.id())).setFirstResult(retValue.getResultsFrom()
						 - 1).setMaxResults(pageSize).list();
						 if (ids.isEmpty()) {
						 List items = new ArrayList();
						 retValue.setResults(items);
						 } else {
						 criteria.setProjection(null);*/
						
						criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
						
						List<T> items = criteria.setFirstResult(
								retValue.getResultsFrom()).setMaxResults(
								pageSize).list();
						retValue.setResults(items);

						return retValue;
					}
				});
	}

	/**
	 * <p>分页查询</p>
	 * 
	 * @param detachedCriteria
	 * @param pageSize 页大小
	 * @param pageNo  页号
	 * @param orderBy 排序
	 * @return
	 * @see 其他相关
	 */
	protected PageResults<T> findPageByCriteria(
			final DetachedCriteria detachedCriteria, final int pageSize,
			final int pageNo, final Order orderBy) {
		return (PageResults<T>) getHibernateTemplate().execute(
				new HibernateCallback<PageResults<T>>() {
					@SuppressWarnings("unchecked")
					public PageResults<T> doInHibernate(Session session)
							throws HibernateException {
						Criteria criteria = detachedCriteria
								.getExecutableCriteria(session);
						Long totalCountObject = (Long) criteria
								.setProjection(Projections.rowCount())
								.uniqueResult();
						int totalCount = (totalCountObject == null) ? 0 : totalCountObject.intValue();

						PageResults<T> retValue = new PageResults<T>();
						retValue.setCurrentPage(pageNo);
						retValue.setPageSize(pageSize);
						retValue.setTotalCount(totalCount);

						criteria.setProjection(null);
						// List
						// ids=criteria.setProjection(Projections.distinct(Projections.id())).list();
						List<PK> ids = new ArrayList<PK>();
						if (orderBy != null) {
							String orderStr = orderBy.toString().split(" ")[0];
							ids = criteria.setProjection(
									Projections.projectionList().add(
											Projections.distinct(Projections
													.id())).add(
											Projections.property(orderStr)))
									.addOrder(orderBy).setFirstResult(
											retValue.getResultsFrom() - 1)
									.setMaxResults(pageSize).list();
							List<PK> idss = new ArrayList<PK>();
							for (int i = 0; i < ids.size(); i++) {
								PK[] o = (PK[]) ids.get(i);
								idss.add(o[0]);
							}
							ids = idss;
						} else {
							ids = criteria.setProjection(
									Projections.distinct(Projections.id()))
									.setFirstResult(
											retValue.getResultsFrom() - 1)
									.setMaxResults(pageSize).list();
						}

						if (ids.isEmpty()) {
							List<T> items = new ArrayList<T>();
							retValue.setResults(items);
						} else {
							criteria.setProjection(null);
							criteria.setResultTransformer(
									CriteriaSpecification.DISTINCT_ROOT_ENTITY)
									.add(Restrictions.in("id", ids));
							List<T> items = criteria.setFirstResult(0)
									.setMaxResults(Integer.MAX_VALUE).list();
							retValue.setResults(items);
						}

						return retValue;
					}
				});
	}
	
	/**
	 * <p>条件查询所有的实体列表</p>
	 * 
	 * @param detachedCriteria
	 * @return
	 * @see 其他相关
	 */
	protected List<T> findAllByCriteria(final DetachedCriteria detachedCriteria) {
		return getHibernateTemplate().execute(new HibernateCallback<List<T>>() {
			@SuppressWarnings("unchecked")
			public List<T> doInHibernate(Session session)
					throws HibernateException {
				Criteria criteria = detachedCriteria
						.getExecutableCriteria(session);
				return criteria.list();
			}
		});
	}

	/**
	 * <p>根据DetachedCriteria条件查询实体数量</p>
	 * 
	 * @param detachedCriteria
	 * @return  实体数
	 * @see 其他相关
	 */
	protected int findCountByCriteria(final DetachedCriteria detachedCriteria) {
		Integer count = (Integer) getHibernateTemplate().execute(
				new HibernateCallback<Integer>() {
					public Integer doInHibernate(Session session)
							throws HibernateException {
						Criteria criteria = detachedCriteria
								.getExecutableCriteria(session);
						return (Integer) criteria.setProjection(Projections.rowCount())
								.uniqueResult();
					}
				});
		return count.intValue();
	}
	
	
	/**
	 * <p>根据DetachedCriteria条件查询实体列表</p>
	 * 
	 * @param detachedCriteria
	 * @return  实体列表
	 */
	protected List<T> findCountByGroup(final DetachedCriteria detachedCriteria) {
		return (List<T>) getHibernateTemplate().execute(new HibernateCallback<List<T>>() {
			@SuppressWarnings("unchecked")
			public List<T> doInHibernate(Session session)
					throws HibernateException {
				final ProjectionList projectionList = Projections
						.projectionList();
				projectionList.add(Projections.groupProperty("groupState"));
				projectionList.add(Projections.rowCount());
				Criteria criteria = detachedCriteria
						.getExecutableCriteria(session);
				criteria.setProjection(projectionList);
				List<T> users = criteria.list();
				return users;
			}
		});
	}

	/**
	 * <p>判断对象是否为null,字段串"",null返回false,其它null返回false</p>
	 * 
	 * @param obj
	 * @return
	 * @see 其他相关
	 */
	protected boolean isNotEmpty(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj instanceof String) {
			if (((String) obj).trim().length() == 0) {
				return false;
			} else {
				return true;
			}
		}
		return true;
	}
	
	/**
	 * <p>判断对象是否为null,字段串"",null返回false,其它null返回false,如果是小于0返回false</p>
	 * 
	 * @param obj
	 * @return
	 * @see 其他相关
	 */
	protected boolean isValidate(Object obj) {
		boolean result = true;
		if (obj == null) {
			result = false;
		}
		if (obj instanceof String) {
			if (((String) obj).trim().length() == 0) {
				result = false;
			} else if(((String) obj).trim().startsWith("-")){
				result = false;
			}else{
				return true;
			}
		}else if(obj instanceof Integer){
			if(((Integer)obj).intValue() < 0 ){
				result =  false;
			}
		}
		return result;
	}
	
	/**
	 * <p>取得数据库服务器当前时间</p>
	 * 
	 * @return Date
	 * @see
	 */
	protected Date getCurrentSysDate(){
		return this.getHibernateTemplate().execute(new HibernateCallback<Date>() {
			
			public Date doInHibernate(Session arg0) throws HibernateException, SQLException {
				return (Date)arg0.createSQLQuery("select sysdate from dual").uniqueResult();
			}
			
		});
	}
}
