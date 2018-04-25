package cn.zjw.bos.service.impl;

import cn.zjw.bos.dao.UserDao;
import cn.zjw.bos.domain.User;
import cn.zjw.bos.service.UserService;
import cn.zjw.bos.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	@Override
	public User selectByUsernameAndPassword(User model) {

		return userDao.selectByUsernameAndPassword(model.getUsername(), MD5Utils.md5(model.getPassword()));
	}

	@Override
	public void editPassword(String editPassword, String password, String id) {
		String p = MD5Utils.md5(password);
		userDao.executeUpdate(editPassword,p,id);
	}
}
