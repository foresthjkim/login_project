package com.dudulim.health.service;

import com.dudulim.health.dto.PredictRequestDto;
import com.dudulim.health.dto.PredictResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class PredictService {

    private final RestTemplate restTemplate;
    private final String flaskPredictUrl;

    public PredictService(
            @Value("${flask.predict-url:http://localhost:5000/predict}") String flaskPredictUrl
    ) {
        this.restTemplate = new RestTemplate();
        this.flaskPredictUrl = flaskPredictUrl;
    }

    public PredictResponseDto predict(PredictRequestDto request) {
        try {
            ResponseEntity<PredictResponseDto> response = restTemplate.postForEntity(
                    flaskPredictUrl,
                    request,
                    PredictResponseDto.class
            );

            PredictResponseDto body = response.getBody();
            if (body == null || body.getResult() == null || body.getResult().isBlank()) {
                throw new IllegalStateException("Flask 서버에서 예측 결과를 받지 못했습니다.");
            }

            return body;
        } catch (RestClientException e) {
            throw new IllegalStateException("Flask 예측 서버와 통신할 수 없습니다.", e);
        }
    }
}
