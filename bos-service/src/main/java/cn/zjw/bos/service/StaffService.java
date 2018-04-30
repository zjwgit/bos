package cn.zjw.bos.service;

import cn.zjw.bos.domain.Staff;
import cn.zjw.bos.utils.PageBean;

import java.util.List;

public interface StaffService {
	public void save(Staff model);

	public void getPageBean(PageBean pageBean);

	public void deleteBatch(String ids);

	public Staff getById(String id);

	public void update(Staff staff);

	public List<Staff> findNoDelete();
}
