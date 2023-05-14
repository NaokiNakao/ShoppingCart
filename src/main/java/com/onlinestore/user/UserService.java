package com.onlinestore.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserService {

    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getFirstName() + " " + userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        // encrypt the password using spring security
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        
        UserRole userRole = userRoleRepository.findByName("CUSTOMER");
        if(userRole == null){
            userRole = checkRoleExist();
        }
        user.setRoles(Arrays.asList(userRole));
        userRepository.save(user);
    }

    private UserRole checkRoleExist() {
        UserRole userRole = new UserRole();
        userRole.setName("CUSTOMER");
        return userRoleRepository.save(userRole);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    private UserDTO mapToUserDTO(User user){
        UserDTO userDto = new UserDTO();
        String[] str = user.getName().split(" ");
        userDto.setFirstName(str[0]);
        userDto.setLastName(str[1]);
        userDto.setEmail(user.getEmail());
        return userDto;
    }

}
