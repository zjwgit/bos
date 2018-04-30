package cn.zjw.bos.service.impl;

import cn.zjw.bos.dao.DecidedzoneDao;
import cn.zjw.bos.dao.SubareaDao;
import cn.zjw.bos.domain.Decidedzone;
import cn.zjw.bos.domain.Subarea;
import cn.zjw.bos.service.DecidedzoneService;
import cn.zjw.bos.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DecidedzoneServiceImpl implements DecidedzoneService {
	@Autowired
	private DecidedzoneDao decidedzoneDao;
	@Autowired
	private SubareaDao subareaDao;
	@Override
	public void save(Decidedzone model, String[] subareaid) {
		decidedzoneDao.save(model);
		for (String s : subareaid) {
			Subarea subarea = subareaDao.selectById(s);
			subarea.setDecidedzone(model);
		}
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		decidedzoneDao.getPageBean(pageBean);
	}
}
