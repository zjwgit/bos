package cn.zjw.bos.service.impl;

import cn.zjw.bos.dao.SubareaDao;
import cn.zjw.bos.domain.Subarea;
import cn.zjw.bos.service.SubareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SubareaServiceImpl implements SubareaService {
	@Autowired
	private SubareaDao subareaDao;
	@Override
	public void save(Subarea model) {
		subareaDao.save(model);
	}
}
