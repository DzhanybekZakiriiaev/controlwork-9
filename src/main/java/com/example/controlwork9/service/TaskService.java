package com.example.controlwork9.service;

import com.example.controlwork9.entity.Task;
import com.example.controlwork9.exception.ResourceNotFoundException;
import com.example.controlwork9.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService extends BaseService<Task, Integer> {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        super(taskRepository);
        this.taskRepository = taskRepository;
    }

    public List<Task> findByDeveloperId(Integer developerId) {
        List<Task> tasks = taskRepository.findByDeveloperId(developerId);
        if (tasks.isEmpty()) {
            throw new ResourceNotFoundException("Tasks not found for developer ID: " + developerId);
        }
        return tasks;
    }

    public Task update(Integer taskId, Task task) {
        Task existingTask = getById(taskId);
        existingTask.setName(task.getName());
        existingTask.setDeveloper(task.getDeveloper());
        existingTask.setStatus(task.getStatus());
        return taskRepository.save(existingTask);
    }

    public Page<Task> getTasks(String status, Pageable pageable) {
        if (status != null) {
            return taskRepository.findByStatus(status, pageable);
        } else {
            return taskRepository.findAll(pageable);
        }
    }
}
