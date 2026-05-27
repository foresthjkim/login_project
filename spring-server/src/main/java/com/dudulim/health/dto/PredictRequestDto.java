package com.dudulim.health.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class PredictRequestDto {

    @NotNull(message = "키를 입력해주세요.")
    @Min(value = 50, message = "키는 50cm 이상 입력해주세요.")
    @Max(value = 250, message = "키는 250cm 이하로 입력해주세요.")
    private Double height;

    @NotNull(message = "몸무게를 입력해주세요.")
    @Min(value = 10, message = "몸무게는 10kg 이상 입력해주세요.")
    @Max(value = 300, message = "몸무게는 300kg 이하로 입력해주세요.")
    private Double weight;

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
