package com.saupay.cardservice;

import com.saupay.cardservice.model.Bank;
import com.saupay.cardservice.repository.BankRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.util.HashSet;

@SpringBootApplication
@EnableEurekaClient
public class CardServiceApplication implements CommandLineRunner {

	private final BankRepository bankRepository;

	public CardServiceApplication(BankRepository bankRepository) {
		this.bankRepository = bankRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(CardServiceApplication.class, args);
	}


	@Override
	public void run(String... args)  {


	}

}
