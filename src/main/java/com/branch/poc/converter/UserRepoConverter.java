package com.branch.poc.converter;

import com.branch.poc.response.UserRepoDto;
import com.branch.poc.service.rest.response.GitHubRepoDto;
import org.apache.commons.collections4.SetUtils;

import java.util.Set;
import java.util.stream.Collectors;

public class UserRepoConverter {
    private UserRepoConverter() {
    }

    public static Set<UserRepoDto> of(Set<GitHubRepoDto> gitHubRepos) {
        return SetUtils.emptyIfNull(gitHubRepos).stream()
                .map(UserRepoConverter::of)
                .collect(Collectors.toSet());
    }

    public static UserRepoDto of(GitHubRepoDto gitHubRepoDto) {
        if (gitHubRepoDto != null) {
            return new UserRepoDto(gitHubRepoDto.repoName(), gitHubRepoDto.repoUrl());
        }
        return null;
    }
}
