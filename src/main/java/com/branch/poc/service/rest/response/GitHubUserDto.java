package com.branch.poc.service.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;

public record GitHubUserDto(
        @JsonProperty("login")
        String userName,
        @JsonProperty("name")
        String displayName,
        @JsonProperty("avatar_url")
        String avatarUrl,
        @JsonProperty("location")
        String locationName,
        @JsonProperty("email")
        String emailAddress,
        @JsonProperty("url")
        String mainGitHubUrl,
        @JsonProperty("created_at")
        ZonedDateTime createDate) {
}
