package com.branch.poc.service.rest.impl;

import com.branch.poc.exception.ApiException;
import com.branch.poc.service.rest.response.GitHubRepoDto;
import com.branch.poc.service.rest.response.GitHubUserDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

import static com.branch.poc.constants.TestConstants.GITHUB_USER;
import static com.branch.poc.constants.TestConstants.GITHUB_USER_REPOS;
import static com.branch.poc.service.rest.impl.GitHubRestServiceImpl.USER_REPO_TYPE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GitHubRestServiceImplTest {

    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private GitHubRestServiceImpl gitHubRestService;

    private static final ResponseEntity<GitHubUserDto> GITHUB_USER_RESPONSE_ENTITY_SUCCESS =
            new ResponseEntity<>(GITHUB_USER, HttpStatus.OK);

    private static final ResponseEntity<Set<GitHubRepoDto>> GITHUB_USER_REPOS_RESPONSE_ENTITY_SUCCESS =
            new ResponseEntity<>(GITHUB_USER_REPOS, HttpStatus.OK);

    private static final HttpClientErrorException INTERNAL_SERVER_ERROR =
            new HttpClientErrorException(HttpStatusCode.valueOf(500));

    private static final HttpClientErrorException ENTITY_NOT_FOUND =
            new HttpClientErrorException(HttpStatusCode.valueOf(404));

    @Test
    public void testGetUser_success() throws ApiException {
        when(restTemplate.exchange(anyString(),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(GitHubUserDto.class),
                anyMap())).thenReturn(GITHUB_USER_RESPONSE_ENTITY_SUCCESS);

        GitHubUserDto actualUserDto =
                gitHubRestService.getUser("testUser");

        assertThat(actualUserDto, equalTo(GITHUB_USER));
    }

    @Test
    public void testGetUser_error() {
        when(restTemplate.exchange(anyString(),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(GitHubUserDto.class),
                anyMap())).thenThrow(INTERNAL_SERVER_ERROR);

        ApiException thrownException = assertThrows(ApiException.class, () ->
                gitHubRestService.getUser("testUser"));

        assertThat(thrownException.getErrorCode(), equalTo(100));
    }

    @Test
    public void testGetUser_userNotFound() {
        when(restTemplate.exchange(anyString(),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(GitHubUserDto.class),
                anyMap())).thenThrow(ENTITY_NOT_FOUND);

        ApiException thrownException = assertThrows(ApiException.class, () ->
                gitHubRestService.getUser("testUser"));

        assertThat(thrownException.getErrorCode(), equalTo(300));
    }

    @Test
    public void testGetUserRepos_success() throws ApiException {
        when(restTemplate.exchange(anyString(),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(USER_REPO_TYPE),
                anyMap())).thenReturn(GITHUB_USER_REPOS_RESPONSE_ENTITY_SUCCESS);

        Set<GitHubRepoDto> actualUserRepos =
                gitHubRestService.getRepositoriesForUser("testUser");

        assertThat(actualUserRepos, equalTo(GITHUB_USER_REPOS));
    }

    @Test
    public void testGetUserRepos_error() {
        when(restTemplate.exchange(anyString(),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(USER_REPO_TYPE),
                anyMap())).thenThrow(INTERNAL_SERVER_ERROR);

        ApiException thrownException = assertThrows(ApiException.class, () ->
                gitHubRestService.getRepositoriesForUser("testUser"));

        assertThat(thrownException.getErrorCode(), equalTo(200));
    }

    @Test
    public void testGetUserRepos_userNotFound() {
        when(restTemplate.exchange(anyString(),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(USER_REPO_TYPE),
                anyMap())).thenThrow(ENTITY_NOT_FOUND);

        ApiException thrownException = assertThrows(ApiException.class, () ->
                gitHubRestService.getRepositoriesForUser("testUser"));

        assertThat(thrownException.getErrorCode(), equalTo(300));
    }
}
