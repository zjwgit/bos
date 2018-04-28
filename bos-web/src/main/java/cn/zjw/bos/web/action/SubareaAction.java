package cn.zjw.bos.web.action;

import cn.zjw.bos.domain.Subarea;
import cn.zjw.bos.service.SubareaService;
import cn.zjw.bos.web.action.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class SubareaAction extends BaseAction<Subarea> {
	@Autowired
	private SubareaService subareaService;
	/**
	 * 添加分区
	 */
	public String add(){
		subareaService.save(model);
		return LIST;
	}
}
