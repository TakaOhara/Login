package com.example.demo.entity;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * ログイン認証用のテーブルはUserDetailsのimplementsが必要
 */
public class UserInfo implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	private int id;

	private String username;
	
	private String email;
	
	private String password;
	
	private boolean enabled;
	
	private List<GrantedAuthority> authorities;
	
	public UserInfo() {};
	
	public UserInfo(String username, String email, String password, boolean enabled) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.enabled = enabled;
	}


	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setAuthorities(List<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }


	@Override
	public String toString() {
		return "UserInfo [username=" + username + ", email=" + email + ", password=" + password + ", enabled=" + enabled
				+ ", authorities=" + authorities + "]";
	}

}