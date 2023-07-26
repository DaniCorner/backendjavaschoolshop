package com.javaschoolshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;



@SpringBootApplication
public class JavaSchoolShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaSchoolShopApplication.class, args);
	}

}
