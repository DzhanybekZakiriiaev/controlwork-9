package com.example.controlwork9.repository;

import com.example.controlwork9.entity.Task;
import com.example.controlwork9.entity.Worklog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorklogRepository extends JpaRepository<Worklog, Integer> {
    List<Worklog> findByTask(Task task);
}
