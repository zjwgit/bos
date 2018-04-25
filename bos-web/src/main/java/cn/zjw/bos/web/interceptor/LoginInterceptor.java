package cn.zjw.bos.web.interceptor;

import cn.zjw.bos.domain.User;
import cn.zjw.bos.utils.LoginUtils;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class LoginInterceptor extends MethodFilterInterceptor {
	private User loginUser;

	@Override
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		//判断session中是否存在loginUser，如果存在放行，不存在转回登录界面
		ActionProxy proxy = actionInvocation.getProxy();
		String url = proxy.getNamespace() + proxy.getActionName() ;
		System.out.println(url);
		User loginUser = LoginUtils.getLoginUser();
		if(loginUser == null){
			return "login";
		}
		return actionInvocation.invoke();
	}
}
