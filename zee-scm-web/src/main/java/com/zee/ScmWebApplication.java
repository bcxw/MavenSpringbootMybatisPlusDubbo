package com.zee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class ScmWebApplication {
	public static void main(String[] args) {
		SpringApplication.run(ScmWebApplication.class, args);
	}
}
