package com.app.tp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String duration;

    @ElementCollection
    private List<String> requiredSkills;

    @ManyToMany
    @JoinTable(
        name = "project_developer",
        joinColumns = @JoinColumn(name = "project_id"),
        inverseJoinColumns = @JoinColumn(name = "developer_id")
    )
    private List<Developer> assignedDevelopers;

    // Custom getter and setter for requiredSkills as comma-separated string
    @Transient
    public String getRequiredSkillsString() {
        if (requiredSkills == null || requiredSkills.isEmpty()) {
            return "";
        }
        return String.join(", ", requiredSkills);
    }

    public void setRequiredSkillsString(String skillsString) {
        if (skillsString != null && !skillsString.isBlank()) {
            this.requiredSkills = Arrays.stream(skillsString.split(","))
                    .map(String::trim)
                    .collect(Collectors.toList());
        }
    }
}
