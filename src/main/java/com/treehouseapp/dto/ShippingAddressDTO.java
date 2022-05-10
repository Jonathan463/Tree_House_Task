package com.treehouseapp.dto;


import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public class ShippingAddressDTO {
    private String fullName;
    private String email;
    private String street;
    private String city;
    private String state;
    private String phone;
    private Boolean defaultAddress = true;
}
