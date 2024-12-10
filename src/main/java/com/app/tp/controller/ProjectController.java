package com.app.tp.controller;

import com.app.tp.model.Developer;
import com.app.tp.model.Project;
import com.app.tp.service.DeveloperService;
import com.app.tp.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    private final DeveloperService developerService;

    @GetMapping
    public String listProjects(Model model) {
        model.addAttribute("projects", projectService.getAllProjects());
        return "projects/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("project", new Project());
        model.addAttribute("developers", developerService.getAllDevelopers());
        return "projects/add";
    }

    @PostMapping("/add")
    public String addProject(@ModelAttribute("project") @Valid Project project, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("developers", developerService.getAllDevelopers());
            return "projects/add";
        }
        projectService.createProject(project);
        return "redirect:/projects";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Project project = projectService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid project Id:" + id));
        model.addAttribute("project", project);
        model.addAttribute("developers", developerService.getAllDevelopers());
        return "projects/edit";
    }

    @PostMapping("/edit/{id}")
    public String editProject(@PathVariable Long id, @ModelAttribute("project") @Valid Project project, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("developers", developerService.getAllDevelopers());
            return "projects/edit";
        }
        project.setId(id);
        projectService.updateProject(project);
        return "redirect:/projects";
    }

    @GetMapping("/delete/{id}")
    public String deleteProject(@PathVariable Long id, Model model) {
        projectService.deleteProject(id);
        return "redirect:/projects";
    }
}
