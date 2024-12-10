package com.app.tp.controller;

import com.app.tp.model.Developer;
import com.app.tp.model.Evaluation;
import com.app.tp.model.Project;
import com.app.tp.service.DeveloperService;
import com.app.tp.service.EvaluationService;
import com.app.tp.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/evaluations")
@RequiredArgsConstructor
public class EvaluationController {

    private final EvaluationService evaluationService;
    private final DeveloperService developerService;
    private final ProjectService projectService;

    @GetMapping
    public String listEvaluations(Model model) {
        model.addAttribute("evaluations", evaluationService.getAllEvaluations());
        return "evaluations/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("evaluation", new Evaluation());
        model.addAttribute("developers", developerService.getAllDevelopers());
        model.addAttribute("projects", projectService.getAllProjects());
        return "evaluations/add";
    }

    @PostMapping("/add")
    public String addEvaluation(@ModelAttribute("evaluation") @Valid Evaluation evaluation, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("developers", developerService.getAllDevelopers());
            model.addAttribute("projects", projectService.getAllProjects());
            return "evaluations/add";
        }
        evaluationService.addEvaluation(evaluation);
        return "redirect:/evaluations";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Evaluation evaluation = evaluationService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid evaluation Id:" + id));
        model.addAttribute("evaluation", evaluation);
        model.addAttribute("developers", developerService.getAllDevelopers());
        model.addAttribute("projects", projectService.getAllProjects());
        return "evaluations/edit";
    }

    @PostMapping("/edit/{id}")
    public String editEvaluation(@PathVariable Long id, @ModelAttribute("evaluation") @Valid Evaluation evaluation, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("developers", developerService.getAllDevelopers());
            model.addAttribute("projects", projectService.getAllProjects());
            return "evaluations/edit";
        }
        evaluation.setId(id);
        evaluationService.updateEvaluation(evaluation);
        return "redirect:/evaluations";
    }

    @GetMapping("/delete/{id}")
    public String deleteEvaluation(@PathVariable Long id, Model model) {
        evaluationService.deleteEvaluation(id);
        return "redirect:/evaluations";
    }
}
