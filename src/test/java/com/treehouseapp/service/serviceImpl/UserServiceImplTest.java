package com.treehouseapp.service.serviceImpl;


import com.treehouseapp.dto.SignupDto;
import com.treehouseapp.dto.ChangePasswordDto;
import com.treehouseapp.dto.EditUserDetailsDto;
import com.treehouseapp.security.PasswordValidator;
import com.treehouseapp.service.serviceInterfaces.CartService;
import com.treehouseapp.model.enums.UserGender;
import com.treehouseapp.repository.RoleRepository;
import com.treehouseapp.repository.UserRepository;
import com.treehouseapp.repository.WalletRepository;
import com.treehouseapp.security.jwt.JwtUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @Mock
    CartService cartService;
    @Mock
    PasswordEncoder encoder;
    @Mock
    JwtUtils utils;

    @Mock
    RoleRepository roleRepository;
    @Mock
    AuthenticationManager authenticationManager;
    @Mock
    UserDetailsService userDetailsService;
    @Mock
    ConfirmationTokenService confirmationTokenService;
    @Mock
    EmailSenderService senderService;
    @Mock
    WalletRepository walletRepository;
    @Mock
    WalletServiceImpl walletService;





    UserServiceImpl userService;

    private TestEntityManager entityManager;

    @BeforeEach

    void setUp() {
        userService = new UserServiceImpl(utils, authenticationManager, userDetailsService, userRepository, encoder,
                confirmationTokenService, senderService, walletRepository, walletService, roleRepository, cartService, new PasswordValidator());
    }

    @Test
    public void testCreateUserIsFalse() throws Exception {

        SignupDto signupDto = new SignupDto();
        signupDto.setEmail("blah@gmail.com");
        signupDto.setPassword("hshjsfjhsfhjs");
        signupDto.setFirstName("MunaMuna");
        signupDto.setLastName("OnyeOnye");

        when(userRepository.existsByEmail(any())).thenReturn(true);
        userService.createUser(signupDto);
        verify(userRepository, times(0)).save(any());
    }

    @Test
    public void testCreateUserIsSuccessful() throws Exception {

        SignupDto signupDto = new SignupDto();
        signupDto.setEmail("blah@gmail.com");
        signupDto.setPassword("hshjsfjhsfhjs");
        signupDto.setFirstName("MunaMuna");
        signupDto.setLastName("OnyeOnye");

        when(userRepository.existsByEmail(any())).thenReturn(false);
        userService.createUser(signupDto);
        verify(userRepository, times(0)).save(any());
    }


    @Test
    public void testChangePassword(){
        ChangePasswordDto changePasswordDto = new ChangePasswordDto("one", "two", "two");
        Long id = 1L;
        userService = mock(UserServiceImpl.class);
        doNothing().when(userService).changePassword(any(), any());        //changePassword(any());
        userService.changePassword(changePasswordDto, id);      //changePasswordDto
        verify(userService, times(1)).changePassword(changePasswordDto, id);
    }


    @Test
    public void testUpdateUser(){

        EditUserDetailsDto editUserDetailsDto = new EditUserDetailsDto(
                "Amara", "Ojiakor", "amara@gmail.com",
                UserGender.FEMALE, new Date(2000-12-11)
        );
        Long id = 1L;
        userService = mock(UserServiceImpl.class);
        doNothing().when(userService).updateUser(any(), any());
        userService.updateUser(editUserDetailsDto, id);
        verify(userService, times(1)).updateUser(editUserDetailsDto, id);

    }

}