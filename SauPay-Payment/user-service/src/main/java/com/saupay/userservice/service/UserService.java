package com.saupay.userservice.service;


import com.ctc.wstx.shaded.msv_core.driver.textui.Debug;
import com.saupay.userservice.dto.UserDto;
import com.saupay.userservice.dto.converter.UserDtoConverter;
import com.saupay.userservice.keycloak.KeycloakUserService;
import com.saupay.userservice.model.User;
import com.saupay.userservice.repository.UserRepository;
import com.saupay.userservice.request.UserResuest;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserDtoConverter userDtoConverter;

    private final KeycloakUserService keycloakUserService;

    public UserService(UserRepository userRepository, UserDtoConverter userDtoConverter, KeycloakUserService keycloakUserService) {
        this.userRepository = userRepository;
        this.userDtoConverter = userDtoConverter;
        this.keycloakUserService = keycloakUserService;
    }


    public UserDto registerUser(UserResuest userRequest) {

        List<UserRepresentation> userRepresentations = keycloakUserService.readUserByEmail(userRequest.getUserEmail());
        if (userRepresentations.size() > 0) {
            throw new RuntimeException("This email already registered as a user. Please check and retry.");
        }
        userRepository.findByEmail(userRequest.getUserEmail()).ifPresent(user -> {
            throw new RuntimeException("This email already registered as a user. Please check and retry.");
        });

        //user attributes set
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setEmail(userRequest.getUserEmail());
        userRepresentation.setEmailVerified(false);
        userRepresentation.setEnabled(true);
        userRepresentation.setUsername(userRequest.getUserName());

        //password set
        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setValue(userRequest.getUserPassword());
        credentialRepresentation.setTemporary(false);
        userRepresentation.setCredentials(Collections.singletonList(credentialRepresentation));

        //user creation Respone Code Number
        Integer userCreationResponse = keycloakUserService.createUser(userRepresentation);
        // ekrana logla
        System.out.println("Return Http Code: " +userCreationResponse);
        if (userCreationResponse == 201)//201 means user created successfully
        {
            log.info("User created under given username {}", userRequest.getUserEmail());

            User user = new User("",userRequest.getUserName(),userRequest.getUserSurname(),userRequest.getUserEmail(),userRequest.getUserPassword(),userRequest.getUserPhone(),userRequest.getUserTC(), LocalDateTime.now());
            UserDto userDto = userDtoConverter.convert(userRepository.save(user));
            return userDto;
        }
        else {
            throw new RuntimeException("We couldn't find user under given identification. Please check and retry");
        }
    }
    public UserDto getUser(String id) {
        return userDtoConverter.convert(userRepository.findById(id).orElse(null));
    }




}
