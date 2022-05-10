package com.treehouseapp.controller;

import com.treehouseapp.dto.ChangePasswordDto;
import com.treehouseapp.dto.EditUserDetailsDto;
import com.treehouseapp.dto.UserDetailsDTO;
import com.treehouseapp.payload.UpdatePayLoad;
import com.treehouseapp.service.serviceImpl.UserDetailsService;
import com.treehouseapp.service.serviceInterfaces.UserServiceInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceInterface userService;
    private final UserDetailsService userDetailsService;


    @PostMapping("/edit_user_details")

    public ResponseEntity<?> updateUserDetails(@RequestBody EditUserDetailsDto userDetailsDto){
       userService.updateUser(userDetailsDto, userService.getUserIDFromSecurityContext());

           return new ResponseEntity<>(userDetailsDto, HttpStatus.OK);

    }


    @PostMapping("/change_password")
    public ResponseEntity<?> updatePassword(@RequestBody ChangePasswordDto changePasswordDto){
         UpdatePayLoad update = userService.changePassword(changePasswordDto, userService.getUserIDFromSecurityContext());

            return new ResponseEntity<>(update, HttpStatus.OK);
    }


    @GetMapping("/details")
    public ResponseEntity<?> getAllUserDetails() throws Exception{
        System.out.println("into method");
        UserDetailsDTO userDetailsDTO = userDetailsService.getUserDetails(userService.getUserIDFromSecurityContext());
        return new ResponseEntity<>(userDetailsDTO,HttpStatus.OK);
    }

}