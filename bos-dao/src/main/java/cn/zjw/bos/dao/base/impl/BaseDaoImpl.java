package cn.zjw.bos.dao.base.impl;

import cn.zjw.bos.dao.base.BaseDao;
import cn.zjw.bos.domain.Staff;
import cn.zjw.bos.utils.PageBean;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
	private Class<T> entityClass;
    @Resource
	public void mySetSessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	public BaseDaoImpl(){
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		Type[] types = type.getActualTypeArguments();
		entityClass = (Class<T>) types[0];
	}

	@Override
	public void save(T entity) {
		getHibernateTemplate().save(entity);
	}

	@Override
	public void delete(T entity) {
    	getHibernateTemplate().delete(entity);
	}

	@Override
	public void update(T entity) {
		getHibernateTemplate().update(entity);
	}

	@Override
	public T selectById(Serializable id) {
		return getHibernateTemplate().get(entityClass,id);
	}

	@Override
	public List<T> findAll() {
		String hql ="From "+entityClass.getSimpleName();
    	return (List<T>) getHibernateTemplate().find(hql);
	}

	@Override
	public void executeUpdate(String queryName, Object... objects) {
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.getNamedQuery(queryName);
		int time = 0;
		for (Object o : objects) {
			query.setParameter(time++,o);
		}
		query.executeUpdate();
	}

	@Override
	public void getPageBean(PageBean pageBean) {
		Integer currentPage = pageBean.getCurrentPage();
		Integer pageSize = pageBean.getPageSize();
		DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();
		//查询总计录数
		detachedCriteria.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>) getHibernateTemplate().findByCriteria(detachedCriteria);
		Long total = list.get(0);
		pageBean.setTotal(total.intValue());
		detachedCriteria.setProjection(null);
		detachedCriteria.setResultTransformer(DetachedCriteria.ROOT_ENTITY);
		//查询rows
		int first = (currentPage-1)*pageSize;
		List<Staff> objects = (List<Staff>) getHibernateTemplate().findByCriteria(detachedCriteria, first, pageSize);
		pageBean.setRows(objects);

	}

	@Override
	public void saveOrUpdate(T entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}

	@Override
	public List<T> findByCriteria(DetachedCriteria detachedCriteria) {
		return (List<T>) getHibernateTemplate().findByCriteria(detachedCriteria);
	}
}
