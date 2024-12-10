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
public class Developer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String login;
    private String password;

    @ElementCollection
    private List<String> skills;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles; // e.g., "ADMIN", "USER"

    // Custom getter and setter for skills as comma-separated string
    @Transient
    public String getSkillsString() {
        if (skills == null || skills.isEmpty()) {
            return "";
        }
        return String.join(", ", skills);
    }

    public void setSkillsString(String skillsString) {
        if (skillsString != null && !skillsString.isBlank()) {
            this.skills = Arrays.stream(skillsString.split(","))
                    .map(String::trim)
                    .collect(Collectors.toList());
        }
    }
}
