package com.saupay.userservice.controller;

import com.saupay.userservice.dto.UserDto;
import com.saupay.userservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("v1/user")
public class UserController {

    private final UserService userService;


    public UserController( UserService userService) {
        this.userService = userService;
    }

   @PostMapping("/createUser")
    public ResponseEntity<UserDto>  createUser(){
       UserDto userDto =userService.createUser();
       return ResponseEntity.ok(userDto);
    }

    @GetMapping("/findUser/{id}")
    public ResponseEntity<UserDto> findCustomer(@PathVariable String id){
        UserDto customerDto =userService.findUser(id);
        return ResponseEntity.ok(customerDto);
    }
}
