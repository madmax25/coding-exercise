package com.branch.poc.converter;

import com.branch.poc.response.UserDto;
import com.branch.poc.response.UserRepoDto;
import com.branch.poc.service.rest.response.GitHubRepoDto;
import com.branch.poc.service.rest.response.GitHubUserDto;
import org.apache.commons.collections4.SetUtils;

import java.util.Set;

public class UserConverter {

    private UserConverter() {
    }

    public static UserDto of(GitHubUserDto gitHubUserDto, Set<GitHubRepoDto> gitHubRepos) {
        if (gitHubUserDto != null) {
            Set<UserRepoDto> userRepos = UserRepoConverter.of(gitHubRepos);
            return new UserDto(gitHubUserDto.userName(),
                    gitHubUserDto.displayName(),
                    gitHubUserDto.avatarUrl(),
                    gitHubUserDto.locationName(),
                    gitHubUserDto.emailAddress(),
                    gitHubUserDto.mainGitHubUrl(),
                    gitHubUserDto.createDate().toLocalDateTime(),
                    SetUtils.emptyIfNull(userRepos));
        }
        return null;
    }
}
