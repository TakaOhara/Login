package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.repository.UserInfoDao;
import com.example.demo.service.UserInfoService;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public UserInfoService userInfoService(UserInfoDao userInfoDao, BCryptPasswordEncoder passwordEncoder) {
        return new UserInfoService(userInfoDao, passwordEncoder);
    }

}