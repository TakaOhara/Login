package com.example.demo.config;

import com.example.demo.repository.UserInfoDao;
import com.example.demo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Web Security設定ファイル
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserInfoService userInfoService;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(createAuthProvider());
    }

    private AuthenticationProvider createAuthProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userInfoService);
        authProvider.setPasswordEncoder(passwordEncoder); 

        return authProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
        .antMatchers("/h2-console/**").permitAll()
        .antMatchers("/admin/**").hasRole("ADMIN")
                // antMatchers mvcMatcherを使う
        .mvcMatchers("/", "/register/**").permitAll()  // **はそれより下の階層全て
        .anyRequest().authenticated()//それ以外は認証無しでのアクセス不可
        .and()
        .formLogin()
        .loginPage("/showMyLoginPage")
        .loginProcessingUrl("/authenticateTheUser")
        .defaultSuccessUrl("/task", true)
        .permitAll()
        .and()
        .logout().permitAll()
        .and()
        .exceptionHandling().accessDeniedPage("/access-denied");
        
        //h2-consoleを表示するためcsrf対策を停止
        http.csrf().disable();
        http.headers().frameOptions().disable();

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //セキュリティ設定を無視
//        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/bootstrap/**");
        web.ignoring().mvcMatchers("/css/**", "/js/**", "/img/**", "/lib/**", "/bootstrap/**", "/webjars/**", "/favicon.ico");
    }


	
}