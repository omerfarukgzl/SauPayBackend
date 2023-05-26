package com.saupay.userservice.service;


import com.saupay.userservice.dto.UserDto;
import com.saupay.userservice.dto.converter.UserDtoConverter;
import com.saupay.userservice.keycloak.KeycloakProperties;
import com.saupay.userservice.keycloak.KeycloakUserService;
import com.saupay.userservice.model.User;
import com.saupay.userservice.repository.UserRepository;
import com.saupay.userservice.request.UserLoginRequest;
import com.saupay.userservice.request.UserRegisterRequest;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.AccessTokenResponse;
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

    private final KeycloakProperties keycloakProperties;


    public UserService(UserRepository userRepository, UserDtoConverter userDtoConverter, KeycloakUserService keycloakUserService, KeycloakProperties keycloakProperties) {
        this.userRepository = userRepository;
        this.userDtoConverter = userDtoConverter;
        this.keycloakUserService = keycloakUserService;
        this.keycloakProperties = keycloakProperties;
    }


    public UserDto registerUser(UserRegisterRequest userRequest) {

        List<UserRepresentation> userRepresentations = keycloakUserService.readUserByEmail(userRequest.getUserEmail());
        if (userRepresentations.size() > 0) {
            throw new RuntimeException("This email already registered as a user. Please check and retry.");
        }
        userRepository.findByUserEmail(userRequest.getUserEmail()).ifPresent(user -> {
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
    // login service - return jwt token
    public AccessTokenResponse loginUser(UserLoginRequest userLoginRequest)
    {
        User user = userRepository.findByUserEmail(userLoginRequest.getEmail()).orElseThrow(() -> new RuntimeException("This email is not registered as a user. Please check and retry."));
        if(!user.getUserPassword().equals(userLoginRequest.getPassword()))
            throw new RuntimeException("Password is not correct. Please check and retry.");

        Keycloak keycloak = keycloakProperties.newKeycloakBuilderWithPasswordCredentials(userLoginRequest.getEmail(), userLoginRequest.getPassword()).build();
        AccessTokenResponse accessTokenResponse;
        accessTokenResponse = keycloak.tokenManager().getAccessToken();
        return accessTokenResponse;

       /* List<UserRepresentation> userRepresentations = keycloakUserService.readUserByEmail(userLoginRequest.getEmail());
        if (userRepresentations.size() == 0) {
            throw new RuntimeException("This email is not registered as a user. Please check and retry.");
        }
        UserRepresentation userRepresentation = userRepresentations.get(0);
        UserDto userDto = userDtoConverter.convert(userRepository.findByUserEmail(userLoginRequest.getEmail()).orElse(null));
        if (userDto == null) {
            throw new RuntimeException("This email is not registered as a user. Please check and retry.");
        }
        if (!userDto.().equals(userLoginRequest.getUserPassword())) {
            throw new RuntimeException("Password is not correct. Please check and retry.");
        }
        return keycloakUserService.generateToken(userRepresentation.getUsername(), userLoginRequest.getUserPassword());*/

    }
    public UserDto getUser(String id) {
        return userDtoConverter.convert(userRepository.findById(id).orElse(null));
    }

    public UserDto getUserByEmail(String email) {
        UserDto userDto = userDtoConverter.convert(userRepository.findByUserEmail(email).orElse(null));
        if (userDto == null) {
            throw new RuntimeException("This email is not registered as a user. Please check and retry.");
        }
        System.out.println("Find User"+userDto);

        return userDto;
    }




}
