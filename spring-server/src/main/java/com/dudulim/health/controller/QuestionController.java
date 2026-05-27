package com.dudulim.health.controller;

import com.dudulim.health.dto.QuestionRequest;
import com.dudulim.health.service.QuestionService;
import jakarta.validation.Valid;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/question/list")
    public String list(Model model) {
        model.addAttribute("questions", questionService.findAll());
        return "question/list";
    }

    @GetMapping("/question/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("question", questionService.findById(id));
        return "question/detail";
    }

    @GetMapping("/question/create")
    public String createForm(Model model) {
        model.addAttribute("questionRequest", new QuestionRequest());
        return "question/form";
    }

    @PostMapping("/question/create")
    public String create(
            @Valid @ModelAttribute("questionRequest") QuestionRequest request,
            BindingResult bindingResult,
            Authentication authentication
    ) {
        if (bindingResult.hasErrors()) {
            return "question/form";
        }

        Long id = questionService.create(request, authentication.getName());
        return "redirect:/question/detail/" + id;
    }

    @GetMapping("/question/{id}/edit")
    public String editForm(
            @PathVariable Long id,
            Authentication authentication,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        try {
            model.addAttribute("questionId", id);
            model.addAttribute("questionRequest", questionService.findUpdateForm(id, authentication.getName(), isAdmin(authentication)));
            return "question/edit";
        } catch (AccessDeniedException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/question/detail/" + id;
        }
    }

    @PostMapping("/question/{id}/edit")
    public String edit(
            @PathVariable Long id,
            @Valid @ModelAttribute("questionRequest") QuestionRequest request,
            BindingResult bindingResult,
            Authentication authentication,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("questionId", id);
            return "question/edit";
        }

        questionService.update(id, request, authentication.getName(), isAdmin(authentication));
        return "redirect:/question/detail/" + id;
    }

    @PostMapping("/question/{id}/delete")
    public String delete(
            @PathVariable Long id,
            Authentication authentication,
            RedirectAttributes redirectAttributes
    ) {
        try {
            questionService.delete(id, authentication.getName(), isAdmin(authentication));
            return "redirect:/question/list";
        } catch (AccessDeniedException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/question/detail/" + id;
        }
    }

    private boolean isAdmin(Authentication authentication) {
        return authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch("ROLE_ADMIN"::equals);
    }
}
