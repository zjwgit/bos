package cn.zjw.bos.utils;

import cn.zjw.bos.domain.User;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;

public class LoginUtils {
	public static HttpSession getSession(){
		return ServletActionContext.getRequest().getSession();
	}
	public static User getLoginUser(){
		return (User) getSession().getAttribute("loginUser");
	}
}
