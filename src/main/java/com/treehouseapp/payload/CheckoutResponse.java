package com.treehouseapp.payload;

import com.treehouseapp.dto.ShippingAddressDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutResponse {
    private long orderid;
    private List<ProductSummary> usersCartItems;
    private ShippingAddressDTO shippingAddressDTO;
}


