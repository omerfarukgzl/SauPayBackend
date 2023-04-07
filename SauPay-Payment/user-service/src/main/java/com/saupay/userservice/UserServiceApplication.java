package com.saupay.userservice;

import com.saupay.userservice.model.User;
import com.saupay.userservice.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@SpringBootApplication
@EnableEurekaClient
public class UserServiceApplication implements CommandLineRunner {

	private final UserRepository userRepository;

	public UserServiceApplication(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		userRepository.save(new User("", "omer","faruk","omer@gmail.com","5355455454","54545454544","5454", LocalDateTime.now()));
	}
}
