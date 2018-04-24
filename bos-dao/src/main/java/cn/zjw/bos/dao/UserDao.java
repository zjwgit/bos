package cn.zjw.bos.dao;

import cn.zjw.bos.dao.base.BaseDao;
import cn.zjw.bos.domain.User;

public interface UserDao extends BaseDao<User>{
	User selectByUsernameAndPassword(String usrname,String password);

}
