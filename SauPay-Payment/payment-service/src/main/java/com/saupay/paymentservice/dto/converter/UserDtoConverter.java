package com.saupay.paymentservice.dto.converter;

import com.saupay.userservice.dto.UserDto;
import com.saupay.userservice.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter {
    public UserDto convert(User from) {
        return new UserDto(from.getId(),
                from.getUserName(),
                from.getUserSurname(),
                from.getUserEmail(),
                from.getUserPhone(),
                from.getUserTC());
    }
}
