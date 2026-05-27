package com.dudulim.health.dto;

public class PredictResponseDto {

    private String result;

    public PredictResponseDto() {
    }

    public PredictResponseDto(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
