package com.treehouseapp.dto;

import com.treehouseapp.model.flowers.MenuItem;
import com.treehouseapp.model.users.ShippingAddress;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Data
public class UserDetailsDTO {
    private String name;

    private String email;

    private ShippingAddress shippingAddressDTO;

    private String walletBalance;

    private ResponseViewUserOrdersDTO viewUserOrdersDTOS;

//    private List<FavoriteMeal> favoriteDto;
    private List<MenuItem> favoriteDto;

}
