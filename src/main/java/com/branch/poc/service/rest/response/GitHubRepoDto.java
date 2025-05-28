package com.branch.poc.service.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GitHubRepoDto(
        @JsonProperty("name")
        String repoName,
        @JsonProperty("url")
        String repoUrl) {
}
