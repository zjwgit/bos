package cn.zjw.bos.utils;

import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public class PageBean {
	//当前页码
	private Integer currentPage;
	//每页显示的记录数
	private Integer pageSize;
	//查询条件
	private DetachedCriteria detachedCriteria;
	//总记录数
	private Integer total;
	//当前页需要展示的数据集合
	private List rows;

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public DetachedCriteria getDetachedCriteria() {
		return detachedCriteria;
	}

	public void setDetachedCriteria(DetachedCriteria detachedCriteria) {
		this.detachedCriteria = detachedCriteria;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}
}
