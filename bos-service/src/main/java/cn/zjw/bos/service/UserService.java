package cn.zjw.bos.service;

import cn.zjw.bos.domain.User;

public interface UserService {

	User selectByUsernameAndPassword(User model);

	void editPassword(String editPassword, String password, String id);
}
