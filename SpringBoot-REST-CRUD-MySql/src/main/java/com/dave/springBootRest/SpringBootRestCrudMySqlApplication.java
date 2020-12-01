package com.dave.springBootRest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/*equivalent to declaring @Configuration, @EnableAutoConfiguration and @ComponentScan.*/
public class SpringBootRestCrudMySqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestCrudMySqlApplication.class, args);
	}

}
