package com.userdata.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.userdata.vo.UserVo;

public class RestClientUtil {
	
	   public void getAllUsersDemo() {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
		    RestTemplate restTemplate = new RestTemplate();
			String url = "http://localhost:8080/userdata/allusers";
		    HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
		    ResponseEntity<UserVo[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, UserVo[].class);
		    UserVo[] userVos = responseEntity.getBody();
		    for(UserVo uservo : userVos) {
		    	System.out.println(uservo.getFirstName());
		    }
	   }
	   
	   public static void main(String[] args){
		   RestClientUtil util= new RestClientUtil();
		   util.getAllUsersDemo();
	   }
}
