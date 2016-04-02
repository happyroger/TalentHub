package com.example.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import model.EmailResponse;

@Controller
public class IndexController {
	@RequestMapping(value="/index", method = RequestMethod.GET)
	public String sayHello() {
		return "index";
	}

	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String login(HttpServletRequest req, Model model){
		if (validateUser(req.getParameter("email").toString(), req.getParameter("pass").toString()))
			return "main";
		else{
			model.addAttribute("invalidUser", "Invalid e-mail or password");
			return "index";
		}
	}
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public String registerUser(HttpServletRequest req){
		if(validateRegistration(req)){
			String text = "Hello " + req.getParameter("firstName").toString() + 
					" !<br>You are successfully registrated at TalentHub";
			EmailResponse.getInstance().SendEmail(req.getParameter("email").toString(), "Welcome to TalentHub!", text);
			return "main";
		}
		else{
			return "index";
		}
	}
	
	private boolean validateRegistration(HttpServletRequest req) {
		// check registration parameters
		return false;
	}
	private boolean validateUser(String email, String pass) {
		return false;
	}
}
