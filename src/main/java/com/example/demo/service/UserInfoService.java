package com.example.demo.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.UserInfo;
import com.example.demo.repository.UserInfoDao;


@Service("UserInfoService")
public class UserInfoService implements UserDetailsService {
	
	private final UserInfoDao userInfoDao;
	
	private final BCryptPasswordEncoder passwordEncoder;

    public UserInfoService(UserInfoDao userInfoDao, BCryptPasswordEncoder passwordEncoder) {
        this.userInfoDao = userInfoDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    	
    	try {
    		UserInfo userInfo = userInfoDao.findByEmail(email);
			return userInfo;
    	}catch(EmptyResultDataAccessException e) {
			throw new UsernameNotFoundException("No user");
		}
	}
	
	@Transactional
	public void save(UserInfo userInfo) {
		String hushPass = passwordEncoder.encode(userInfo.getPassword());
		userInfo.setPassword(hushPass);
		userInfoDao.insert(userInfo);
	}

}