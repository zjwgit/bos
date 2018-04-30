package cn.zjw.bos.service.impl;

import cn.zjw.bos.dao.StaffDao;
import cn.zjw.bos.domain.Staff;
import cn.zjw.bos.service.StaffService;
import cn.zjw.bos.utils.PageBean;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {
	@Autowired
	private StaffDao staffDao;
	@Override
	public void save(Staff model) {
		staffDao.save(model);
	}

	@Override
	public void getPageBean(PageBean pageBean) {
		staffDao.getPageBean(pageBean);
	}

	@Override
	public void deleteBatch(String ids) {
		if(StringUtils.isNotBlank(ids)){
			String[] strings = ids.split(",");
			for (String id : strings){
				staffDao.executeUpdate("staff.delete",id);
			}
		}
	}

	@Override
	public Staff getById(String id) {
		return staffDao.selectById(id);
	}

	@Override
	public void update(Staff staff) {
		staffDao.update(staff);
	}

	@Override
	public List<Staff> findNoDelete() {
		DetachedCriteria criteria = DetachedCriteria.forClass(Staff.class);
		criteria.add(Restrictions.eq("deltag","0"));
		return  staffDao.findByCriteria(criteria);
	}
}
