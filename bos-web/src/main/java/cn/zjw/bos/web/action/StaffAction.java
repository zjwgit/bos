package cn.zjw.bos.web.action;

import cn.zjw.bos.domain.Staff;
import cn.zjw.bos.service.StaffService;
import cn.zjw.bos.web.action.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {
	@Autowired
	private StaffService service;


	public String save() throws Exception {
		service.save(model);
		return LIST;
	}
}
