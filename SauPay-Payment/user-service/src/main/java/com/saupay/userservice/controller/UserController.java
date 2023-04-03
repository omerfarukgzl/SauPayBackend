package com.saupay.userservice.controller;

import com.saupay.userservice.dto.UserDto;
import com.saupay.userservice.request.UserResuest;
import com.saupay.userservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    private final UserService userService;


    public UserController( UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserResuest userRequest){
        return ResponseEntity.ok(userService.registerUser(userRequest));
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(){
        return ResponseEntity.ok("login");
    }

    @PostMapping("/hello")
    public ResponseEntity<String> login(@RequestBody UserResuest userRequest){
        return ResponseEntity.ok("hello");
    }


    @GetMapping("/getUser/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable String id){
        UserDto customerDto =userService.getUser(id);
        return ResponseEntity.ok(customerDto);
    }


}
