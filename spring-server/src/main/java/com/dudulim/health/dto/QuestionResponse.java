package com.dudulim.health.dto;

import com.dudulim.health.domain.Question;

import java.time.LocalDateTime;

public class QuestionResponse {

    private final Long id;
    private final String title;
    private final String content;
    private final String writerUsername;
    private final String writerName;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public QuestionResponse(Question question) {
        this.id = question.getId();
        this.title = question.getTitle();
        this.content = question.getContent();
        this.writerUsername = question.getWriter() == null ? null : question.getWriter().getUsername();
        this.writerName = question.getWriter() == null ? "작성자 없음" : question.getWriter().getName();
        this.createdAt = question.getCreatedAt();
        this.updatedAt = question.getUpdatedAt();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getWriterUsername() {
        return writerUsername;
    }

    public String getWriterName() {
        return writerName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
