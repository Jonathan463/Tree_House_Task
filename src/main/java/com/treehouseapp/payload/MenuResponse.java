package com.treehouseapp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuResponse {
    private String message;

    private String productName;

    private Double price;

    private String description;

    private String category;

    private String image;

}
