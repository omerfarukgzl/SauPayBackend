package com.saupay.domainservice.clients.user_client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service" ,path = "v1/user")
public interface UserServiceClient {


    @GetMapping("/getUser/{id}")
    ResponseEntity<UserDto> getUser(@PathVariable String id);

    @GetMapping("/getUserByUserEmail/{email}")
    ResponseEntity<UserDto> getUserByUserEmail(@PathVariable String email);

}
