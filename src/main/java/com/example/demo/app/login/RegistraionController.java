package com.example.demo.app.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.UserInfo;
import com.example.demo.service.UserInfoService;

@Controller
@RequestMapping("/register")
public class RegistraionController {
	
	private final UserInfoService userInfoService;//インターフェース名に変更したほうがよい

	@Autowired
	public RegistraionController(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}
	
	@GetMapping("/showRegistrationForm")
	public String showMyLoginPage(
			@ModelAttribute("RegistrationForm") RegistrationForm registrationForm,
			Model model) {
		registrationForm.setNewRegistration(true);
		model.addAttribute("registrationForm", registrationForm);
		model.addAttribute("title", "会員登録");
		return "login/registration-form";
	}
	
	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(
				@Validated @ModelAttribute("registrationForm") RegistrationForm registrationForm,
				BindingResult result,
				Model model) {

		 if (!result.hasErrors()){
			 	UserInfo userInfo = makeUserInfo(registrationForm);
	   		 	userInfoService.save(userInfo);
			 	return "login/registration-confirmation";
	        }else {
	        	registrationForm.setNewRegistration(true);
	        	model.addAttribute("registrationForm", registrationForm);
	        	model.addAttribute("title", "会員登録");
	    		return "login/registration-form";
	        }
		 
	}

	
	private UserInfo makeUserInfo(RegistrationForm registrationForm) {
		return new UserInfo(registrationForm.getUsername(), registrationForm.getEmail(),
				registrationForm.getPassword(), true);
	}
	

}