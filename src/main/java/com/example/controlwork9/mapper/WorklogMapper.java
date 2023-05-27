package com.example.controlwork9.mapper;

import com.example.controlwork9.dto.WorklogDTO;
import com.example.controlwork9.entity.Task;
import com.example.controlwork9.entity.Worklog;
import com.example.controlwork9.exception.WorklogNotFoundException;
import com.example.controlwork9.service.TaskService;
import com.example.controlwork9.service.WorklogService;
import org.springframework.stereotype.Component;

@Component
public class WorklogMapper {

    private final WorklogService worklogService;

    private final TaskService taskService;

    public WorklogMapper(WorklogService worklogService, TaskService taskService) {
        this.worklogService = worklogService;
        this.taskService = taskService;
    }

    public WorklogDTO toDTO(Worklog worklog) {
        WorklogDTO dto = new WorklogDTO();
        dto.setId(worklog.getId());
        dto.setTaskId(worklog.getTask().getId());
        dto.setTimeSpent(worklog.getTimeSpent());
        dto.setDescription(worklog.getDescription());
        return dto;
    }

    public Worklog toEntity(WorklogDTO dto) {
        Worklog worklog = new Worklog();
        worklog.setId(dto.getId());
        Task task = new Task();
        task.setId(dto.getTaskId());
        worklog.setTask(task);
        worklog.setTimeSpent(dto.getTimeSpent());
        worklog.setDescription(dto.getDescription());
        return worklog;
    }

    public WorklogDTO addWorklog(Integer taskId, WorklogDTO worklogDTO) {
        Task task = taskService.getById(taskId);
        if (task != null) {
            Worklog worklog = new Worklog();
            worklog.setTask(task);
            worklog.setTimeSpent(worklogDTO.getTimeSpent());
            worklog.setDescription(worklogDTO.getDescription());

            Worklog addedWorklog = worklogService.save(worklog);
            return toDTO(addedWorklog);
        }

        throw new WorklogNotFoundException("Task not found with ID: " + taskId);
    }

    public WorklogDTO getWorklogById(Integer worklogId) {
        Worklog worklog = worklogService.getById(worklogId);
        if (worklog != null) {
            return toDTO(worklog);
        }
        throw new WorklogNotFoundException("Worklog not found with ID: " + worklogId);
    }
}
