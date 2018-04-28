package cn.zjw.bos.web.action.base;

import cn.zjw.bos.utils.PageBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;


public class BaseAction<T> extends ActionSupport implements ModelDriven<T>{
	protected PageBean pageBean = new PageBean();
	//创建离线提交查询对象
	DetachedCriteria detachedCriteria = null;

	public void setPage(int page) {
		pageBean.setCurrentPage(page);
	}

	public void setRows(int rows) {
		pageBean.setPageSize(rows);
	}

	/**
	 * 将指定Java对象转为json，并响应到客户端页面
	 * @param o
	 * @param exclueds
	 */
	public void java2Json(Object o ,String[] exclueds){
		JsonConfig jsonConfig = new JsonConfig();
		//指定哪些属性不需要转json
		jsonConfig.setExcludes(exclueds);
		String json = JSONObject.fromObject(o,jsonConfig).toString();
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * 将指定Java对象转为json，并响应到客户端页面
	 * @param o
	 * @param exclueds
	 */
	public void java2Json(List o , String[] exclueds){
		JsonConfig jsonConfig = new JsonConfig();
		//指定哪些属性不需要转json
		jsonConfig.setExcludes(exclueds);
		String json = JSONArray.fromObject(o,jsonConfig).toString();
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected static final String HOME = "home";
	protected static final String LIST = "list";
	protected T model;
	@Override
	public T getModel() {
		return model;
	}
	public BaseAction(){
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		Type[] types = type.getActualTypeArguments();
		Class<T> type1 = (Class<T>) types[0];
		detachedCriteria = DetachedCriteria.forClass(type1);
		pageBean.setDetachedCriteria(detachedCriteria);
		try {
			model = type1.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
