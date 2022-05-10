package com.treehouseapp.dto;

import com.treehouseapp.model.enums.UserGender;
import lombok.*;

import java.sql.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditUserDetailsDto {
    private String firstname;
    private String lastname;
    private String email;
    private UserGender gender;
    private Date dateOfBirth;

}
