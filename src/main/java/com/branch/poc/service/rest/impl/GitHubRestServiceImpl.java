package com.branch.poc.service.rest.impl;

import com.branch.poc.exception.ApiException;
import com.branch.poc.service.rest.GitHubService;
import com.branch.poc.service.rest.response.GitHubRepoDto;
import com.branch.poc.service.rest.response.GitHubUserDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.branch.poc.constants.Constants.API_ERROR_GETTING_USER_DETAILS;
import static com.branch.poc.constants.Constants.API_ERROR_GETTING_USER_REPO_DETAILS;
import static com.branch.poc.constants.Constants.API_ERROR_USER_NOT_FOUND;

@Service
public class GitHubRestServiceImpl implements GitHubService {
    static final ParameterizedTypeReference<Set<GitHubRepoDto>> USER_REPO_TYPE =
            new ParameterizedTypeReference<>() {
            };

    private static final String GITHUB_VERSION_PARAM = "X-GitHub-Api-Version";
    private static final String GITHUB_VERSION_VALUE = "2022-11-28";
    private static final String GITHUB_USER_AGENT_PARAM = "User-Agent";
    private static final String GITHUB_USER_AGENT_VALUE = "Coding-Exercise-App";

    private static final String GITHUB_CONTENT_TYPE = "application/vnd.github+json";

    private static final String GET_GITHUB_USER = "https://api.github.com/users/{userName}";
    private static final String GET_GITHUB_REPOSITORIES_BY_USER = "https://api.github.com/users/{userName}/repos";

    private static final HttpHeaders HTTP_HEADERS;
    private static final HttpEntity<String> REQUEST_ENTITY;

    static {
        HTTP_HEADERS = new HttpHeaders();
        HTTP_HEADERS.setContentType(MediaType.APPLICATION_JSON);
        HTTP_HEADERS.setAccept(List.of(MediaType.valueOf(GITHUB_CONTENT_TYPE)));
        HTTP_HEADERS.set(GITHUB_VERSION_PARAM, GITHUB_VERSION_VALUE);
        HTTP_HEADERS.set(GITHUB_USER_AGENT_PARAM, GITHUB_USER_AGENT_VALUE);

        REQUEST_ENTITY = new HttpEntity<>(null, HTTP_HEADERS);
    }

    private final RestTemplate restTemplate;

    public GitHubRestServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public GitHubUserDto getUser(String userName) throws ApiException {
        Map<String, String> uriVariables = createUriVariables(userName);

        try {
            return restTemplate.exchange(GET_GITHUB_USER,
                            HttpMethod.GET,
                            REQUEST_ENTITY,
                            GitHubUserDto.class,
                            uriVariables)
                    .getBody();
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new ApiException(String.format("User '%s' not found", userName), API_ERROR_USER_NOT_FOUND);
            }
            throw new ApiException("Error getting user details", API_ERROR_GETTING_USER_DETAILS);
        }
    }

    @Override
    public Set<GitHubRepoDto> getRepositoriesForUser(String userName) throws ApiException {
        Map<String, String> uriVariables = createUriVariables(userName);

        try {
            return restTemplate.exchange(GET_GITHUB_REPOSITORIES_BY_USER,
                            HttpMethod.GET,
                            REQUEST_ENTITY,
                            USER_REPO_TYPE,
                            uriVariables)
                    .getBody();
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new ApiException(String.format("User '%s' not found", userName), API_ERROR_USER_NOT_FOUND);
            }
            throw new ApiException("Error getting user repo details", API_ERROR_GETTING_USER_REPO_DETAILS);
        }
    }

    private static Map<String, String> createUriVariables(String userName) {
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("userName", userName);
        return uriVariables;
    }
}
