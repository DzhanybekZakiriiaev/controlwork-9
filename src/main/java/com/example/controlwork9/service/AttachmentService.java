package com.example.controlwork9.service;

import com.example.controlwork9.entity.Attachment;
import com.example.controlwork9.entity.Task;
import com.example.controlwork9.exception.ResourceNotFoundException;
import com.example.controlwork9.repository.AttachmentRepository;
import com.example.controlwork9.util.FileDownloadUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class AttachmentService extends BaseService<Attachment, Integer> {
    private final AttachmentRepository attachmentRepository;
    private final TaskService taskService;

    public AttachmentService(AttachmentRepository attachmentRepository, TaskService taskService) {
        super(attachmentRepository);
        this.attachmentRepository = attachmentRepository;
        this.taskService = taskService;
    }

    public List<Attachment> findByTask(Task task) {
        List<Attachment> attachments = attachmentRepository.findByTask(task);
        if (attachments.isEmpty()) {
            throw new ResourceNotFoundException("Attachments not found for task: " + task.getId());
        }
        return attachments;
    }


    public Attachment getAttachment(Integer attachmentId) {
        return attachmentRepository.findById(attachmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Attachment not found with ID: " + attachmentId));
    }

    private String generateUniqueFilename(String originalFilename) {
        String extension = StringUtils.getFilenameExtension(originalFilename);
        String uniqueFilename = UUID.randomUUID().toString();
        return uniqueFilename + "." + extension;
    }
}
