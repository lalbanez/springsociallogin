package br.com.leandroalbanez.springsociallogin.service;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
//HttpHead, HttpPut, HttpGet, etc...
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

public class HttpGetRequest {

	
	public static String demo (String uri) throws IOException {
		return demo(uri, "", "");		
	}
	
	public static String demo(String uri, String access_token, String access_secret) throws IOException {
		if(access_token.equals("") && access_secret.equals("")) {
			access_token = "73184573-s0SCHTHrJ8RGZnfdkf9pYD8HX96cycsFfW2lpqYSf";
			access_secret = "16O13hMnQFvRRc15XXilaB65qEZjWeUFScF0FuqRxHzAd";
		}
		
		String consumer_key = "h68EKDayD6mx3FuhszVBc8x3a";
		String consumer_secret = "9otjPzMf6ryIWJ6T3Xrtd6bUG23UvOcm5ayYumvo3SLX9hMy7c";

		OAuthConsumer consumer = new CommonsHttpOAuthConsumer(consumer_key, consumer_secret);
		consumer.setTokenWithSecret(access_token, access_secret);
		/*REQUEST TOKEN*/

		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(uri);
		try {
			consumer.sign(httpget);
		} catch (OAuthMessageSignerException ex) {
			Logger.getLogger(HttpRequest.class.getName()).log(Level.SEVERE, null, ex);
		} catch (OAuthExpectationFailedException ex) {
			Logger.getLogger(HttpRequest.class.getName()).log(Level.SEVERE, null, ex);
		} catch (OAuthCommunicationException ex) {
			Logger.getLogger(HttpRequest.class.getName()).log(Level.SEVERE, null, ex);
		}
		HttpResponse response = httpclient.execute(httpget);
		System.out.println(response.getStatusLine().toString());
		HttpEntity entity = response.getEntity();
		System.out.println();
		String retorno = EntityUtils.toString(entity);
		return retorno;
		
	}

	public static void main(String[] args) {
		try {
			demo(args[0]);
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
	}
}