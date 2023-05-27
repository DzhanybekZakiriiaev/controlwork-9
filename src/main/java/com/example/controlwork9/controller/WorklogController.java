package com.example.controlwork9.controller;

import com.example.controlwork9.dto.WorklogDTO;
import com.example.controlwork9.mapper.WorklogMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks/{taskId}/worklogs")
public class WorklogController {
    private final WorklogMapper worklogMapper;

    public WorklogController(WorklogMapper worklogMapper) {
        this.worklogMapper = worklogMapper;
    }


    @PostMapping
    public WorklogDTO addWorklog(@PathVariable("taskId") Integer taskId, @RequestBody WorklogDTO worklogDTO) {
       return worklogMapper.addWorklog(taskId, worklogDTO);
    }

    @GetMapping("/{id}")
    public WorklogDTO getWorklogById(@PathVariable("id") Integer worklogId) {
        return worklogMapper.getWorklogById(worklogId);
    }
}
