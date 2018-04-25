package cn.zjw.bos.service.impl;

import cn.zjw.bos.dao.StaffDao;
import cn.zjw.bos.domain.Staff;
import cn.zjw.bos.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {
	@Autowired
	private StaffDao staffDao;
	@Override
	public void save(Staff model) {
		staffDao.save(model);
	}
}
