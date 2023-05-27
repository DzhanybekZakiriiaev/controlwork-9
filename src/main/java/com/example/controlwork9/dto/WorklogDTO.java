package com.example.controlwork9.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorklogDTO {
    private Integer taskId;
    private String timeSpent;
    private String description;
}
