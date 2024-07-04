package br.com.leandroalbanez.springsociallogin.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.leandroalbanez.springsociallogin.service.HttpGetRequest;

@Controller
public class AuthrorizeController {
	
	@GetMapping("/callback")
	public String callback(@RequestParam("oauth_verifier") String oauth_verifier,
			@RequestParam("oauth_token") String oauth_token, Model model) throws IOException {
		
		String uri = "https://api.twitter.com/oauth/access_token?oauth_token=%s&oauth_verifier=%s";
		uri = String.format(uri, oauth_token, oauth_verifier);
		
		String retornoApi = HttpGetRequest.getAssinatura(uri);
		String[] params = retornoApi.split("&");
		String oauth_token_key = params[0].split("=")[1];
		String oauth_token_secret = params[1].split("=")[1];
		
		uri = "https://api.twitter.com/1.1/account/verify_credentials.json";
		retornoApi = HttpGetRequest.getAssinatura(uri,oauth_token_key, oauth_token_secret);
		
		model.addAttribute("jsonReturn", retornoApi);
		
		return "result";
	}

}
