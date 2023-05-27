package com.example.controlwork9.controller;

import com.example.controlwork9.dto.TaskDTO;
import com.example.controlwork9.mapper.TaskMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskMapper taskMapper;

    public TaskController(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }


    @PostMapping
    public TaskDTO createTask(@RequestBody TaskDTO task) {
        return taskMapper.save(task);
    }

    @GetMapping("/{id}")
    public TaskDTO getTaskById(@PathVariable("id") Integer taskId) {
        return taskMapper.getById(taskId);
    }

    @PutMapping("/{id}")
    public TaskDTO update(@PathVariable("id") Integer taskId, @RequestBody TaskDTO task) {
        return taskMapper.update(taskId, task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable("id") Integer taskId) {
        taskMapper.delete(taskId);
    }

    @GetMapping
    public List<TaskDTO> getTasks(@RequestParam(value = "status", required = false) String status,
                               @RequestParam(value = "page", defaultValue = "0") int page,
                               @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<TaskDTO> taskPage = taskMapper.getTasks(status, pageable);
        return taskPage.getContent();
    }
}
