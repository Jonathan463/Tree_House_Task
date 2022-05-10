package com.treehouseapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginCartItemDto {
    private double amount;
    private long productId;
    private String productImage;
    private double productPrice;
    private String title;
    private Integer productQuantity;
}
