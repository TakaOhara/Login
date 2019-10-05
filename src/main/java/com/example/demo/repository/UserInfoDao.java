package com.example.demo.repository;

import java.util.Optional;

import com.example.demo.entity.UserInfo;

public interface UserInfoDao {
	
	UserInfo findByEmail(String email);
	
	void insert(UserInfo userInfo);

}