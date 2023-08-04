package com.malak.BoardAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class BoardApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoardApiApplication.class, args);
		System.out.println("this working fine");
	}

}
