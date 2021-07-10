package com.occultus.handledata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class HandleDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(HandleDataApplication.class, args);
	}

}
