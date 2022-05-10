package com.treehouseapp;

import com.treehouseapp.model.enums.UserRole;
import com.treehouseapp.model.users.Role;
import com.treehouseapp.model.users.User;
import com.treehouseapp.repository.RoleRepository;
import com.treehouseapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StartUpService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    public void initiateStartup(){
        List<UserRole> roles = Arrays.asList(UserRole.USER, UserRole.ADMIN);
        for (UserRole newRole: roles) {
            Optional<Role> existingRoles = roleRepository.findByName(newRole);
            if(existingRoles.isEmpty()){
                Role role = new Role();
                role.setName(newRole);
                roleRepository.save(role);
            }
        }
    }

    public void createAdmin(){
        Optional<User> existingAdmin = userRepository.findByRole_Name(UserRole.ADMIN);
        if(existingAdmin.isEmpty()){
            User user = new User();
            user.setEmail("admin@chompapp.com");
            user.setLastName("admin");
            user.setFirstName("admin");
            user.setPassword(encoder.encode("Admin@1234"));
            user.setEnabled(true);
            Role role = roleRepository.findByName(UserRole.ADMIN).get();
            user.setRole(role);
            userRepository.save(user);
        }



    }


}