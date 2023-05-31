package com.saupay.userservice.controller;

import com.saupay.userservice.dto.UserDto;
import com.saupay.userservice.request.UserLoginRequest;
import com.saupay.userservice.request.UserRegisterRequest;
import com.saupay.userservice.response.RegisterResponse;
import com.saupay.userservice.response.Response;
import com.saupay.userservice.service.UserService;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<RegisterResponse> registerUser(@Valid @RequestBody UserRegisterRequest userRequest){
        return ResponseEntity.ok(userService.registerUser(userRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<AccessTokenResponse> login(@Valid @RequestBody UserLoginRequest userLoginRequest){
        return ResponseEntity.ok(userService.loginUser(userLoginRequest));
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable String id){
        UserDto user =userService.getUser(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/getUserByUserEmail/{email}")
    public ResponseEntity<UserDto> getUserByUserEmail(@PathVariable String email){
        UserDto user =userService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }



}
