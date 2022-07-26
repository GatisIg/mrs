package com.movierating.mrs;

import com.movierating.mrs.movies.Movies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
public class MrsApplication {

	public static void main(String[] args) {

			SpringApplication.run(MrsApplication.class, args);
	}



}
