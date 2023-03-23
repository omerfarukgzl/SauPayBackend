package com.saupay.domainservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients // Feign Client olmak zorunda. Domain diğer servisleri çağırmak için Feign Client olması gerekiyor.
@EnableEurekaClient// Eureka Client olmak zorunda. Gateway loadbalance yaparken Eureka Client olması gerekiyor.
public class DomainServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DomainServiceApplication.class, args);
	}

}
