package cn.zjw.bos.service;

import cn.zjw.bos.domain.Region;
import cn.zjw.bos.utils.PageBean;

import java.util.List;

public interface RegionService {
	public void saveBatch(List<Region> regionList);

	public void pageQuery(PageBean pageBean);

	public List<Region> findAll();

	public List<Region> findListByQ(String q);

}
