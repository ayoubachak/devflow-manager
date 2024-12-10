package com.app.tp.controller;

import com.app.tp.model.Developer;
import com.app.tp.service.DeveloperService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/developers")
@RequiredArgsConstructor
public class DeveloperController {

    private final DeveloperService developerService;

    @GetMapping
    public String listDevelopers(Model model) {
        model.addAttribute("developers", developerService.getAllDevelopers());
        return "developers/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("developer", new Developer());
        return "developers/add";
    }

    @PostMapping("/add")
    public String addDeveloper(@ModelAttribute("developer") @Valid Developer developer, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "developers/add";
        }
        developerService.saveDeveloper(developer);
        return "redirect:/developers";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Developer developer = developerService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid developer Id:" + id));
        model.addAttribute("developer", developer);
        return "developers/edit";
    }

    @PostMapping("/edit/{id}")
    public String editDeveloper(@PathVariable Long id, @ModelAttribute("developer") @Valid Developer developer, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            developer.setId(id);
            return "developers/edit";
        }
        developerService.saveDeveloper(developer);
        return "redirect:/developers";
    }

    @GetMapping("/delete/{id}")
    public String deleteDeveloper(@PathVariable Long id, Model model) {
        developerService.deleteDeveloper(id);
        return "redirect:/developers";
    }
}
