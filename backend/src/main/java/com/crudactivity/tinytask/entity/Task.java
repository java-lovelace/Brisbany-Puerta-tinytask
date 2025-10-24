package com.crudactivity.tinytask.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * The type Task.
 */
@Data
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private boolean done;

}
