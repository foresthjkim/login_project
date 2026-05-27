package com.dudulim.health.dto;

import com.dudulim.health.domain.Member;
import com.dudulim.health.domain.MemberRole;

import java.time.LocalDateTime;

public class MemberResponse {

    private final Long id;
    private final String username;
    private final String name;
    private final MemberRole role;
    private final LocalDateTime createdAt;

    public MemberResponse(Member member) {
        this.id = member.getId();
        this.username = member.getUsername();
        this.name = member.getName();
        this.role = member.getRole();
        this.createdAt = member.getCreatedAt();
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public MemberRole getRole() {
        return role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
