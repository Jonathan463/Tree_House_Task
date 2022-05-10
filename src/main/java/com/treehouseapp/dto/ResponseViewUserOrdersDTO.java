package com.treehouseapp.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseViewUserOrdersDTO implements Serializable {
    List<ViewUserOrdersDTO> allOrders;
    ShippingAddressDTO shippingAddress;
    PaymentDetailsDTO paymentDetails;


    @Override
    public String toString() {
        return "ResponseViewUserOrdersDTO {" +
                "allOrders: " + allOrders +
                ", shippingAddress: " + shippingAddress +
                ", paymentDetails: " + paymentDetails +
                "}";
    }
}
