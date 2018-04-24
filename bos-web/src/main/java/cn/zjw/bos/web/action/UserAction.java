package cn.zjw.bos.web.action;

import cn.zjw.bos.domain.User;
import cn.zjw.bos.service.UserService;
import cn.zjw.bos.web.action.base.BaseAction;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User>{
	private String checkcode;

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	@Autowired
	private UserService userService;

	public String login() throws Exception {
		String result =LOGIN;
		//比较验证码，如果不同转到login.jsp
		String check = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
		//验证码相同，开始判断传入的username和password是否能获得已经存在的用户
		if(StringUtils.isNotBlank(checkcode) && checkcode.equals(check)){
			User user = userService.selectByUsernameAndPassword(model);
			if(user != null){
				ServletActionContext.getRequest().getSession().setAttribute("loginUser",user);
				result = HOME;
			}else {
				this.addActionError("用户名密码错误,请重新登录");
			}

		}else {
			this.addActionError("验证码错误,请重新登录");
		}
			return result;

	}

	public String logout() throws Exception {
		ServletActionContext.getRequest().getSession().invalidate();
		return LOGIN;
	}
}
