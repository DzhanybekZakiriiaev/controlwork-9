package com.example.controlwork9.service;

import com.example.controlwork9.entity.Task;
import com.example.controlwork9.entity.Worklog;
import com.example.controlwork9.exception.ResourceNotFoundException;
import com.example.controlwork9.repository.WorklogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorklogService extends BaseService<Worklog, Integer> {
    private final WorklogRepository worklogRepository;

    public WorklogService(WorklogRepository worklogRepository) {
        super(worklogRepository);
        this.worklogRepository = worklogRepository;
    }

    public List<Worklog> findByTask(Task task) {
        List<Worklog> worklogs = worklogRepository.findByTask(task);
        if (worklogs.isEmpty()) {
            throw new ResourceNotFoundException("Worklogs not found for task: " + task.getId());
        }
        return worklogs;
    }
}
