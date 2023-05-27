package com.example.controlwork9.mapper;

import com.example.controlwork9.dto.AttachmentDTO;
import com.example.controlwork9.entity.Attachment;
import com.example.controlwork9.entity.Task;
import com.example.controlwork9.service.AttachmentService;
import com.example.controlwork9.service.TaskService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class AttachmentMapper {

    private final AttachmentService attachmentService;

    private final TaskService taskService;

    public AttachmentMapper(AttachmentService attachmentService, TaskService taskService) {
        this.attachmentService = attachmentService;
        this.taskService = taskService;
    }

    public AttachmentDTO toDTO(Attachment attachment) {
        AttachmentDTO dto = new AttachmentDTO();
        dto.setId(attachment.getId());
        dto.setTaskId(attachment.getTask().getId());
        dto.setFilename(attachment.getFilename());
        return dto;
    }

    public Attachment toEntity(AttachmentDTO dto) {
        Attachment attachment = new Attachment();
        attachment.setId(dto.getId());
        Task task = new Task();
        task.setId(dto.getTaskId());
        attachment.setTask(task);
        attachment.setFilename(dto.getFilename());
        return attachment;
    }

    public String uploadAttachment(Integer taskId, MultipartFile file) {
        String fileName = file.getOriginalFilename();
        Path path = Paths.get("src/main/resources/static/images/" + fileName);
        try {
            byte[] data = file.getBytes();
            Files.write(path, data);
            Attachment attachment = new Attachment();
            attachment.setFilename(fileName);
            attachment.setTask(taskService.getById(taskId));
            attachmentService.save(attachment);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "main";
    }

    public ResponseEntity<byte[]> downloadImage(Integer attachmentId) {
        String imagePath = attachmentService.getById(attachmentId).getFilename();
        Resource resource = new FileSystemResource(imagePath);
        if (resource.exists()) {
            try {
                byte[] imageBytes = Files.readAllBytes(resource.getFile().toPath());
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG);
                return ResponseEntity.ok().headers(headers).body(imageBytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ResponseEntity.notFound().build();
    }
}
