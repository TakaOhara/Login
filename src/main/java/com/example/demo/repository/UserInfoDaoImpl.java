package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.UserInfo;

@Repository
public class UserInfoDaoImpl implements UserInfoDao {
	
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public UserInfoDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	@Transactional(readOnly = true)
	public UserInfo findByEmail(String email) throws EmptyResultDataAccessException{
		String sqlUser = "SELECT id, username, email, password, enabled "
				+ "FROM user_info WHERE email = ?";
		
		String sqlAuth = "SELECT role_key, role_comment "
				+ "FROM role INNER JOIN permission "
				+ "ON role.id = permission.role_id "
				+ "WHERE permission.user_info_id = ?";
		
		
		Map<String, Object> result = jdbcTemplate.queryForMap(sqlUser, email);
		List<Map<String, Object>> results = jdbcTemplate.queryForList(sqlAuth, result.get("id"));
		
		UserInfo userInfo = new UserInfo();
		userInfo.setId((int)result.get("id"));
		userInfo.setUsername((String)result.get("username"));
		userInfo.setEmail((String)result.get("email"));
		userInfo.setPassword((String)result.get("password"));
		userInfo.setEnabled((boolean)result.get("enabled"));
		
		List<GrantedAuthority> list = new ArrayList<>();
		
		for(Map<String, Object> auth : results) {
			GrantedAuthority authority = new SimpleGrantedAuthority((String)auth.get("role_key")) ;
			list.add(authority);
		}
		
		userInfo.setAuthorities(list);
			
		return userInfo;

	}

	@Override
	public void insert(UserInfo userInfo) {
		jdbcTemplate.update("INSERT INTO user_info(username, email, password, enabled) VALUES(?, ?, ?, ?)",
				 userInfo.getUsername(), userInfo.getEmail(), userInfo.getPassword(), userInfo.getEnabled() );
	}

}
