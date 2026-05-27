package com.dudulim.health.controller;

import com.dudulim.health.dto.PredictRequestDto;
import com.dudulim.health.dto.PredictResponseDto;
import com.dudulim.health.service.PredictService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PredictController {

    private final PredictService predictService;

    public PredictController(PredictService predictService) {
        this.predictService = predictService;
    }

    @GetMapping("/predict")
    public String predictForm(Model model) {
        model.addAttribute("predictRequest", new PredictRequestDto());
        return "predict/form";
    }

    @PostMapping("/predict")
    public String predict(
            @Valid @ModelAttribute("predictRequest") PredictRequestDto request,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            return "predict/form";
        }

        try {
            PredictResponseDto response = predictService.predict(request);
            model.addAttribute("request", request);
            model.addAttribute("response", response);
            return "predict/result";
        } catch (IllegalStateException e) {
            bindingResult.reject("predictError", e.getMessage());
            return "predict/form";
        }
    }
}
