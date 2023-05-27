package com.example.controlwork9.mapper;

import com.example.controlwork9.config.SecurityConfig;
import com.example.controlwork9.dto.RegisterDTO;
import com.example.controlwork9.dto.TaskDTO;
import com.example.controlwork9.dto.UserDTO;
import com.example.controlwork9.entity.Task;
import com.example.controlwork9.entity.User;
import com.example.controlwork9.exception.NoAccessException;
import com.example.controlwork9.service.TaskService;
import com.example.controlwork9.service.UserService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    private final TaskService taskService;
    private final UserService userService;

    private final TaskMapper taskMapper;

    public UserMapper(TaskService taskService, UserService userService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.userService = userService;
        this.taskMapper = taskMapper;
    }

    public User create(RegisterDTO userDTO) {
        if (userService.findByEmail(SecurityConfig.getCurrentUserEmail()).isManager()) {
            User user = new User();
            user.setEmail(userDTO.getEmail());
            user.setPassword(userDTO.getPassword());
            user.setType(userDTO.getType());
            return userService.save(user);
        }
        throw new NoAccessException("You do not have access to create a user.");
    }


    public UserDTO getUserById(Integer userId) {
        User user = userService.getById(userId);
        return toDTO(user);
    }

    public List<TaskDTO> getUserTasks(Integer userId) {
        if (userService.findByEmail(SecurityConfig.getCurrentUserEmail()).isManager())
        {
            List<Task> tasks = taskService.getAll();
            return tasks.stream()
                    .map(taskMapper::toDTO)
                    .collect(Collectors.toList());
        }
       else {
            List<Task> tasks = taskService.findByDeveloperId(userId);
            return tasks.stream()
                    .map(taskMapper::toDTO)
                    .collect(Collectors.toList());
        }
    }

    public UserDTO updateUser(Integer userId, User user) {
        User updatedUser = userService.updateUser(userId, user);
        return toDTO(updatedUser);
    }

    public void deleteUser(Integer userId) {
        userService.delete(userId);
    }

    public UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setType(user.getType());
        return dto;
    }

}
