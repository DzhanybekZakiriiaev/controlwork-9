package com.example.controlwork9.repository;

import com.example.controlwork9.entity.Attachment;
import com.example.controlwork9.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {
    List<Attachment> findByTask(Task task);
}
