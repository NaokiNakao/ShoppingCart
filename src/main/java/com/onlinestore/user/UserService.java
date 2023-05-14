package com.onlinestore.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getFirstName() + " " + userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        // encrypt the password using spring security
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setUserRole(UserRole.CUSTOMER);
        userRepository.save(user);
    }

/*    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }*/

    private UserDTO mapToUserDTO(User user){
        UserDTO userDto = new UserDTO();
        String[] str = user.getName().split(" ");
        userDto.setFirstName(str[0]);
        userDto.setLastName(str[1]);
        userDto.setEmail(user.getEmail());
        return userDto;
    }

}
