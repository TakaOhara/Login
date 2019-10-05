package com.example.demo.app.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * ログイン画面
 */
@Controller
public class LoginController {

    @GetMapping("/showMyLoginPage") // Anotationのvalueは省略可
    public String login() {
        return "login/plain-login";
    }

    @GetMapping("/access-denied")
    public String showAccessDenied(Model model) {

    	model.addAttribute("title", "Access-Denied");
        return "login/access-denied";

    }

}