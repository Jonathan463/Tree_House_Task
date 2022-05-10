package com.treehouseapp.service.serviceInterfaces;

import com.treehouseapp.dto.ChangePasswordDto;
import com.treehouseapp.dto.EditUserDetailsDto;
import com.treehouseapp.dto.SignupDto;
import com.treehouseapp.dto.UserDto;
import com.treehouseapp.payload.MessageResponse;
import com.treehouseapp.payload.UpdatePayLoad;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;


public interface UserServiceInterface {

    public ResponseEntity<MessageResponse> createUser(SignupDto signupDto);

    public ResponseEntity<?> loginUser (@RequestBody UserDto loginRequest, HttpServletResponse response) throws Exception;

    UpdatePayLoad changePassword(ChangePasswordDto changePasswordDto, Long id);

    void updateUser(EditUserDetailsDto editUserDetailsDto, Long id);

    ResponseEntity<?> resendToVerifyEmail(UserDto loginRequest);

    Long getUserIDFromSecurityContext();

    ResponseEntity<?> confirmToken(String token);
}
