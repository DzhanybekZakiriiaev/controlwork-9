package com.example.controlwork9.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class TaskDTO {
    private Integer id;
    private String name;
    private LocalDate createdDate;
    private Integer developerId;
    private String status;
    private List<AttachmentDTO> attachments;
}
