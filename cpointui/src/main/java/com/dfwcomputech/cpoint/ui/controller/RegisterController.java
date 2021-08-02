package com.dfwcomputech.cpoint.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RegisterController {

	@GetMapping("/register")
	public ModelAndView get() {
		log.info("Preparing registration page");
		return new ModelAndView("registration");
	}
	
	//@PostMapping
	public void post() {
		log.info("Register User");
	}
}
