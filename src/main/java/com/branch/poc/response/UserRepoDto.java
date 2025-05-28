package com.branch.poc.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserRepoDto(
        @JsonProperty("name")
        String repoName,
        @JsonProperty("url")
        String repoUrl) {
}
