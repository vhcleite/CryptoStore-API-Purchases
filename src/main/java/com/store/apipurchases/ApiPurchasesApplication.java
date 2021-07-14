package com.store.apipurchases;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ApiPurchasesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiPurchasesApplication.class, args);
	}

}
