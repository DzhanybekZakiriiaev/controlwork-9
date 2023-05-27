package com.example.controlwork9.repository;

import com.example.controlwork9.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByDeveloperId(Integer developerId);
    Page<Task> findByStatus(String status, Pageable pageable);
}
