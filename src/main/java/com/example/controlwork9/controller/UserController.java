package com.example.controlwork9.controller;

import com.example.controlwork9.dto.RegisterDTO;
import com.example.controlwork9.dto.TaskDTO;
import com.example.controlwork9.dto.UserDTO;
import com.example.controlwork9.entity.User;
import com.example.controlwork9.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserMapper userMapper;

    public UserController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @PostMapping("/create")
    public User createUser(@RequestBody RegisterDTO user) {
        return userMapper.create(user);
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable("id") Integer userId) {
        return userMapper.getUserById(userId);
    }

    @GetMapping("/{id}/tasks")
    public List<TaskDTO> getUserTasks(@PathVariable("id") Integer userId) {
        return userMapper.getUserTasks(userId);
    }

    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable("id") Integer userId, @RequestBody User user) {
        return userMapper.updateUser(userId, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Integer userId) {
        userMapper.deleteUser(userId);
    }
}
