package com.treehouseapp.controller;

import com.treehouseapp.dto.SignupDto;
import com.treehouseapp.dto.UserDto;
import com.treehouseapp.service.serviceImpl.SignupErrorValidationService;
import com.treehouseapp.service.serviceInterfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping("/auth")
public class AuthController {

    private final UserServiceInterface userServiceImpl;
    private final SignupErrorValidationService validationService;

    @Autowired
    public AuthController(UserServiceInterface userServiceImpl, SignupErrorValidationService validationService) {
        this.userServiceImpl = userServiceImpl;
        this.validationService = validationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupDto signupDto, BindingResult result) {
        ResponseEntity<?> errorMap = validationService.validate(result);
        if(errorMap != null) return errorMap;
        return userServiceImpl.createUser(signupDto);

    }

    @GetMapping("/confirm")
    public ResponseEntity<?> confirm(@RequestParam("token") String token) {
        return userServiceImpl.confirmToken(token);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser (@RequestBody UserDto loginRequest, HttpServletResponse response) throws Exception {

        return userServiceImpl.loginUser(loginRequest, response);
     }



}
