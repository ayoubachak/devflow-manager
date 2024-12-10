package com.app.tp.service;

import com.app.tp.model.Developer;
import com.app.tp.repository.DeveloperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeveloperService {
    private final DeveloperRepository developerRepository;
    private final PasswordEncoder passwordEncoder;

    public List<Developer> getAllDevelopers() {
        return developerRepository.findAll();
    }

    public Developer saveDeveloper(Developer developer) {
        developer.setPassword(passwordEncoder.encode(developer.getPassword()));
        // Assign default role, e.g., "USER"
        developer.setRoles(List.of("USER"));
        return developerRepository.save(developer);
    }

    public Optional<Developer> findById(Long id) {
        return developerRepository.findById(id);
    }

    public Developer registerDeveloper(Developer developer) {
        developer.setPassword(passwordEncoder.encode(developer.getPassword()));
        developer.setRoles(List.of("USER")); // Assign default role
        return developerRepository.save(developer);
    }

    public void deleteDeveloper(Long id) {
        developerRepository.deleteById(id);
    }
}
