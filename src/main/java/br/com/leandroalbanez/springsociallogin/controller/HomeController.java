package br.com.leandroalbanez.springsociallogin.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.leandroalbanez.springsociallogin.service.HttpGetRequest;

@Controller
public class HomeController {

	@GetMapping("/")
	public String init(Model model) throws IOException {
		model.addAttribute("urlTwitter", HttpGetRequest.returnAuthTokenUrl());
		return "index";
	}
	
	@GetMapping("/result")
	public String result() {
		return "result";
	}

}
