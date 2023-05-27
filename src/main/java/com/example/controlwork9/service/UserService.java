package com.example.controlwork9.service;

import com.example.controlwork9.entity.User;
import com.example.controlwork9.exception.ResourceNotFoundException;
import com.example.controlwork9.exception.UserNotFoundException;
import com.example.controlwork9.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User, Integer> {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }

    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new ResourceNotFoundException("User not found for email: " + email);
        }
        return user;
    }

    public User updateUser(Integer userId, User userDTO) {
        User user = getById(userId);
        if (user != null) {
            user.setEmail(userDTO.getEmail());
            user.setType(userDTO.getType());
            User updatedUser = save(user);
            return updatedUser;
        }
        throw new UserNotFoundException("User not found with ID: " + userId);
    }

}
