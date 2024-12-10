package com.app.tp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Developer developer;

    @ManyToOne
    private Project project;

    private int rating; // 1 to 5
    private String feedback;
}
