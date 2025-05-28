package com.branch.poc.service.rest;

import com.branch.poc.exception.ApiException;
import com.branch.poc.service.rest.response.GitHubRepoDto;
import com.branch.poc.service.rest.response.GitHubUserDto;

import java.util.Set;

public interface GitHubService {
    GitHubUserDto getUser(String userName) throws ApiException;

    Set<GitHubRepoDto> getRepositoriesForUser(String userName) throws ApiException;
}
