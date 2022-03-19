package com.chompfooddeliveryapp.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private String email;
    private String password;
    private List<LoginCartItemDto> cartItems;
}
