package cn.zjw.bos.web.action.base;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


public class BaseAction<T> extends ActionSupport implements ModelDriven<T>{
	protected static final String HOME = "home";
	protected T model;
	@Override
	public T getModel() {
		return model;
	}
	public BaseAction(){
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		Type[] types = type.getActualTypeArguments();
		Class<T> type1 = (Class<T>) types[0];
		try {
			model = type1.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
