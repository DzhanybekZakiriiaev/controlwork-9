package com.example.controlwork9.controller;

import com.example.controlwork9.mapper.AttachmentMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/tasks/{taskId}/attachments")
public class AttachmentController {
    private final AttachmentMapper attachmentMapper;

    public AttachmentController(AttachmentMapper attachmentMapper) {
        this.attachmentMapper = attachmentMapper;
    }


    @PostMapping
    public String uploadAttachment(@PathVariable("taskId") Integer taskId, @RequestParam("file") MultipartFile file) throws IOException {
      return attachmentMapper.uploadAttachment(taskId, file);
    }


    @GetMapping("/{id}")
    public ResponseEntity<byte[]> downloadAttachment(@PathVariable("id") Integer attachmentId) {
        return attachmentMapper.downloadImage(attachmentId);
    }
}
