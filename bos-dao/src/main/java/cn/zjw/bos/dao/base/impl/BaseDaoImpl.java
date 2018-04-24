package cn.zjw.bos.dao.base.impl;

import cn.zjw.bos.dao.base.BaseDao;
import org.hibernate.SessionFactory;
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
}
