package com.treehouseapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayStackRequest {
    private String email;
    private long amount;
    private String[] channels;
    private String reference;
    private String callback_url;
}
