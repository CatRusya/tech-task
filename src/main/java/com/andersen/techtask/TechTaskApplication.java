package com.andersen.techtask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class  TechTaskApplication {

	public static void main(String[] args) {

		SpringApplication.run(TechTaskApplication.class, args);

	}

}
