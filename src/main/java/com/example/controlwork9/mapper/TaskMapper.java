package com.example.controlwork9.mapper;

import com.example.controlwork9.dto.TaskDTO;
import com.example.controlwork9.entity.Task;
import com.example.controlwork9.entity.User;
import com.example.controlwork9.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class TaskMapper {

    private final AttachmentMapper attachmentMapper;
    private final TaskService taskService;

    public TaskMapper(AttachmentMapper attachmentMapper, TaskService taskService) {
        this.attachmentMapper = attachmentMapper;
        this.taskService = taskService;
    }

    public TaskDTO save(TaskDTO taskDTO) {
        Task task = toEntity(taskDTO);
        Task createdTask = taskService.save(task);
        return toDTO(createdTask);
    }

    public TaskDTO getById(Integer taskId) {
        Task task = taskService.getById(taskId);
        return toDTO(task);
    }

    public TaskDTO update(Integer taskId, TaskDTO taskDTO) {
        Task task = toEntity(taskDTO);
        Task updatedTask = taskService.update(taskId, task);
        return toDTO(updatedTask);
    }

    public void delete(Integer taskId) {
        taskService.delete(taskId);
    }

    public Page<TaskDTO> getTasks(String status, Pageable pageable) {
        Page<Task> taskPage = taskService.getTasks(status, pageable);
        return taskPage.map(this::toDTO);
    }

    public TaskDTO toDTO(Task task) {
        TaskDTO dto = new TaskDTO();
        dto.setId(task.getId());
        dto.setName(task.getName());
        dto.setCreatedDate(task.getCreatedDate());
        dto.setDeveloperId(task.getDeveloper().getId());
        dto.setStatus(task.getStatus());
        dto.setAttachments(task.getAttachments().stream()
                .map(attachmentMapper::toDTO)
                .collect(Collectors.toList()));
        return dto;
    }

    public Task toEntity(TaskDTO dto) {
        Task task = new Task();
        task.setId(dto.getId());
        task.setName(dto.getName());
        task.setCreatedDate(dto.getCreatedDate());
        User developer = new User();
        developer.setId(dto.getDeveloperId());
        task.setDeveloper(developer);
        task.setStatus(dto.getStatus());
        task.setAttachments(dto.getAttachments().stream()
                .map(attachmentMapper::toEntity)
                .collect(Collectors.toList()));
        return task;
    }
}

