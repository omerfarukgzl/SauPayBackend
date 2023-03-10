package com.omer.Payment.controller;

import com.omer.Payment.dto.CustomerAccountDto;
import com.omer.Payment.dto.CustomerDto;
import com.omer.Payment.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerController {

    private final CustomerService customerService;


    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

   @PostMapping("/createCustomer")
    public ResponseEntity<CustomerDto>  createCustomer(){
       CustomerDto customerDto =customerService.createCustomer();
       return ResponseEntity.ok(customerDto);
    }

    @GetMapping("/findCustomer/{id}")
    public ResponseEntity<CustomerDto> findCustomer(@PathVariable String id){
        CustomerDto customerDto =customerService.findCustomer(id);
        return ResponseEntity.ok(customerDto);
    }
}
