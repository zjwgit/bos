package cn.zjw.bos.service;

import cn.zjw.bos.domain.Subarea;
import cn.zjw.bos.utils.PageBean;

import java.util.List;

public interface SubareaService {
	public void save(Subarea model);

	public void pageQuery(PageBean pageBean);

	public List<Subarea> findAll();

	public List<Subarea> findListNotAssocation();
}
