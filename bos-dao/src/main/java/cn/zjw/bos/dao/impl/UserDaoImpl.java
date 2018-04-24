package cn.zjw.bos.dao.impl;

import cn.zjw.bos.dao.UserDao;
import cn.zjw.bos.dao.base.impl.BaseDaoImpl;
import cn.zjw.bos.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
	@Override
	public User selectByUsernameAndPassword(String usrname,String password) {
		String hql = "FROM User u WHERE u.username=? AND u.password=? ";
		List<User> list = (List<User>) getHibernateTemplate().find(hql, usrname,password);
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null ;
	}
}
