package com.branch.poc.service.impl;

import com.branch.poc.converter.UserConverter;
import com.branch.poc.exception.ApiException;
import com.branch.poc.response.UserDto;
import com.branch.poc.service.UserService;
import com.branch.poc.service.rest.GitHubService;
import com.branch.poc.service.rest.response.GitHubRepoDto;
import com.branch.poc.service.rest.response.GitHubUserDto;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final GitHubService gitHubService;

    public UserServiceImpl(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    @Override
    @Cacheable("userInfo")
    public UserDto getUser(String userName) throws ApiException {
        GitHubUserDto gitHubUserDto = gitHubService.getUser(userName);
        Set<GitHubRepoDto> gitHubRepos = gitHubService.getRepositoriesForUser(userName);
        return UserConverter.of(gitHubUserDto, gitHubRepos);
    }
}
