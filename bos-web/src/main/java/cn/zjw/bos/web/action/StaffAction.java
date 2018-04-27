package cn.zjw.bos.web.action;

import cn.zjw.bos.domain.Staff;
import cn.zjw.bos.service.StaffService;
import cn.zjw.bos.utils.PageBean;
import cn.zjw.bos.web.action.base.BaseAction;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {
	@Autowired
	private StaffService service;
	//当前页
	private Integer page;
	//每页显示多少记录数
	private Integer rows;
	private String ids;

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String save() throws Exception {
		service.save(model);
		return LIST;
	}
	//返回分页数据
	public String queryPagebean() throws Exception {
		PageBean pageBean = new PageBean();
		pageBean.setCurrentPage(page);
		pageBean.setPageSize(rows);
		pageBean.setDetachedCriteria(DetachedCriteria.forClass(Staff.class));
		service.getPageBean(pageBean);

		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[]{"currentPage","pageSize","detachedCriteria"});
		String json = JSONObject.fromObject(pageBean, config).toString();
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().write(json);
		return NONE;
	}

	public String deleteBatch() throws Exception {
		service.deleteBatch(ids);
		return LIST;
	}
	public String edit() throws Exception {
		Staff staff = service.getById(model.getId());
		staff.setHaspda(model.getHaspda());
		staff.setName(model.getName());
		staff.setStandard(model.getStandard());
		staff.setStation(model.getStation());
		staff.setTelephone(model.getTelephone());
		service.update(staff);
		return LIST;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}
}
