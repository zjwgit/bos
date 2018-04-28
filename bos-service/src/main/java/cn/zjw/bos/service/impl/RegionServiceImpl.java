package cn.zjw.bos.service.impl;

import cn.zjw.bos.dao.RegionDao;
import cn.zjw.bos.domain.Region;
import cn.zjw.bos.service.RegionService;
import cn.zjw.bos.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class RegionServiceImpl implements RegionService {
	@Autowired
	private RegionDao regionDao;
	@Override
	public void saveBatch(List<Region> regionList) {
		for (Region region : regionList) {
			regionDao.saveOrUpdate(region);
		}
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		regionDao.getPageBean(pageBean);
	}

	@Override
	public List<Region> findAll() {
		return regionDao.findAll();
	}

	@Override
	public List<Region> findListByQ(String q) {
		return regionDao.findListByQ(q);
	}
}
