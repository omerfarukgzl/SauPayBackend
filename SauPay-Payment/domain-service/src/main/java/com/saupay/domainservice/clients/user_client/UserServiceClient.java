package com.saupay.domainservice.clients.user_client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "user-service" ,path = "v1/user")
public interface UserServiceClient {

    @PostMapping("/createUser")
     ResponseEntity<UserDto> createUser();

    @GetMapping("/getUser/{id}")
     ResponseEntity<UserDto> getUser(@PathVariable String id);
}
