package com.example.demo.config;

import java.security.Principal;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.entity.UserInfo;
import com.example.demo.service.TaskNotFoundException;


@ControllerAdvice
public class WebMvcControllerAdvice {

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        // 入力値の空文字をnullに変換
        dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
    
	@ExceptionHandler(TaskNotFoundException.class)
	public String handleException(TaskNotFoundException e,Model model) {
		model.addAttribute("message", e);
		return "error/CustomPage";
	}
	
    @ModelAttribute
    public void addSomeObjects( Model model, Principal principal) {
    	
    	int id = 0;
    	String username = null;
    	
	    	//認証情報の取得
	   if(principal !=  null) {//認証前はnull
	    	Authentication auth = (Authentication)principal;
	        UserInfo userInfo = (UserInfo)auth.getPrincipal();
	        id = userInfo.getId();
	        username = userInfo.getUsername();
	   }
        
        model.addAttribute("userId", id);
        model.addAttribute("username", username);
    }
    
}