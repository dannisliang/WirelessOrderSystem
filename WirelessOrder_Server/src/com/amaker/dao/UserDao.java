package com.amaker.dao;

import com.amaker.entity.User;

public interface UserDao {

	public User login(String account,String password);
}
