package com.example.demo.app.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * こちらは今回使用しません。
 * ルートでアクセスしたときにtest.htmlを表示
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@GetMapping
	public String showDashboard() {
		return "admin/admin";
	}

}