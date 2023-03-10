package com.saupay.userservice.service;


import com.saupay.userservice.dto.UserDto;
import com.saupay.userservice.dto.converter.UserDtoConverter;
import com.saupay.userservice.model.User;
import com.saupay.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserDtoConverter userDtoConverter;

    public UserService(UserRepository userRepository, UserDtoConverter userDtoConverter) {
        this.userRepository = userRepository;
        this.userDtoConverter = userDtoConverter;
    }


    public UserDto createUser() {
        User user = new User("", "Omer", "Faruk", "guzelomerfaruk@gmail.com", "5555", "132344343443", "1234", LocalDateTime.now());
        UserDto userDto = userDtoConverter.convert(userRepository.save(user));
        return userDto;
    }

    public UserDto findUser(String id) {
        return userDtoConverter.convert(userRepository.findById(id).orElse(null));
    }




}
