package cn.zjw.bos.web.action;

import cn.zjw.bos.domain.Decidedzone;
import cn.zjw.bos.service.DecidedzoneService;
import cn.zjw.bos.web.action.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
@Scope("prototype")
public class DecidedzoneAction extends BaseAction<Decidedzone> {
	private String[] subareaid;
	public void setSubareaid(String[] subareaid) {
		this.subareaid = subareaid;
	}

	@Autowired
	private DecidedzoneService decidedzoneService;

	/**
	 * 添加定区
	 */
	public String add(){
		decidedzoneService.save(model,subareaid);
		return LIST;
	}
	/**
	 * 分页查询方法
	 */
	public String pageQuery() throws IOException {
		decidedzoneService.pageQuery(pageBean);
		this.java2Json(pageBean, new String[]{"currentPage","detachedCriteria",
				"pageSize","subareas","decidedzones"});
		return NONE;
	}


}
