package com.treehouseapp.payload;

import com.treehouseapp.model.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {

    private String token;

    private String type;

    private Long id;

    private UserRole role;
}
