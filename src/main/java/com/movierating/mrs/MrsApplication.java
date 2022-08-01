package com.movierating.mrs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class MrsApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {

			SpringApplication.run(MrsApplication.class, args);
	}

}
