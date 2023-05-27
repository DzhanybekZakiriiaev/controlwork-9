package com.example.controlwork9.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Worklog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @Column(name = "time_spent", length = 6, nullable = false)
    private String timeSpent;

    @Column(name = "description", length = 50, nullable = false)
    private String description;

}
