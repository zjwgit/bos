package cn.zjw.bos.dao.base;

import cn.zjw.bos.utils.PageBean;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T> {
	//增
	public void save(T entity);
	//删
	public void delete(T entity);
	//改
	public void update(T entity);
	//根据id查
	public T selectById(Serializable id);
	//差所有
	public List<T> findAll();
	//更新通用
	public void executeUpdate(String queryName,Object...objects);
	//获取分页信息
	public void getPageBean(PageBean pageBean);

}
