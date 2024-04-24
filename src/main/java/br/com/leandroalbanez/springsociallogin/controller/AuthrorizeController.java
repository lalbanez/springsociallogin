package br.com.leandroalbanez.springsociallogin.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.leandroalbanez.springsociallogin.service.HttpGetRequest;

@RestController
public class AuthrorizeController {
	
	@GetMapping("/")
	public String hello() throws IOException {
		String uri = "https://api.twitter.com/oauth/request_token?oauth_callback=%s";
		uri = String.format(uri, "http%3A%2F%2Flocalhost%3A8080%2Fcallback");
		String retornoApi = HttpGetRequest.demo(uri);
		
		String[] params = retornoApi.split("&");
		String authToken = params[0];
		String urlToken = "<a href=\"https://api.twitter.com/oauth/authorize?oauth_token=" + authToken.split("=")[1] + "\" >Login</a>";
		
		return urlToken;
	}
	
	
	@GetMapping("/callback")
	public String callback(@RequestParam("oauth_verifier") String oauth_verifier,
			@RequestParam("oauth_token") String oauth_token) throws IOException {
		
		String uri = "https://api.twitter.com/oauth/access_token?oauth_token=%s&oauth_verifier=%s";
		uri = String.format(uri, oauth_token, oauth_verifier);
		
		String retornoApi = HttpGetRequest.demo(uri);
		/*oauth_token=73184573-s0SCHTHrJ8RGZnfdkf9pYD8HX96cycsFfW2lpqYSf
		 *&oauth_token_secret=16O13hMnQFvRRc15XXilaB65qEZjWeUFScF0FuqRxHzAd
		 *&user_id=73184573
		 *&screen_name=lalbanez 
		 */
		String[] params = retornoApi.split("&");
		String oauth_token_key = params[0].split("=")[1];
		String oauth_token_secret = params[1].split("=")[1];
		
		uri = "https://api.twitter.com/1.1/account/verify_credentials.json";
		retornoApi = HttpGetRequest.demo(uri,oauth_token_key, oauth_token_secret);
		
		return retornoApi;
	}

}
