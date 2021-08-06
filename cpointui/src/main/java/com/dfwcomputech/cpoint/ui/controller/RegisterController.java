package com.dfwcomputech.cpoint.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.dfwcomputech.cpoint.ui.form.RegisterForm;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RegisterController {

	@GetMapping("/register")
	public String get(Model model) {
		log.info("Preparing registration page");
		model.addAttribute("registerForm", new RegisterForm());
		return "registration";
	}
	
	@PostMapping("/register")
	public String post(@ModelAttribute RegisterForm form, Model model) {
		log.info("Register User");
		log.info("{}",form);
		
		model.addAttribute("registerForm", form);
		return "sucess";
	}
}
