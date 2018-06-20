package com.userdata.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.userdata.config.ApplicationConfiguration;
import com.userdata.service.IUserService;

public class TestApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		 AbstractApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
		 IUserService userService = (IUserService) context.getBean("userService");
	 
		 System.out.println(userService.getAllUsers());
	 
	        context.close();
	    }

}
