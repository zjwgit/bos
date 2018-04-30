package cn.zjw.bos.service.impl;

import cn.zjw.bos.dao.SubareaDao;
import cn.zjw.bos.domain.Subarea;
import cn.zjw.bos.service.SubareaService;
import cn.zjw.bos.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SubareaServiceImpl implements SubareaService {
	@Autowired
	private SubareaDao subareaDao;
	@Override
	public void save(Subarea model) {
		subareaDao.save(model);
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		subareaDao.getPageBean(pageBean);
	}

	@Override
	public List<Subarea> findAll() {
		return subareaDao.findAll();
	}

	@Override
	public List<Subarea> findListNotAssocation() {
		DetachedCriteria criteria = DetachedCriteria.forClass(Subarea.class);
		criteria.add(Restrictions.isNull("decidedzone"));
		return subareaDao.findByCriteria(criteria);
	}
}
