package com.branch.poc.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.Set;

public record UserDto(
        @JsonProperty("user_name")
        String userName,
        @JsonProperty("display_name")
        String displayName,
        @JsonProperty("avatar")
        String avatar,
        @JsonProperty("geo_location")
        String geoLocation,
        @JsonProperty("email")
        String email,
        @JsonProperty("url")
        String url,
        @JsonProperty("created_at")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime createDate,
        @JsonProperty("repos")
        Set<UserRepoDto> userRepos) {
}
