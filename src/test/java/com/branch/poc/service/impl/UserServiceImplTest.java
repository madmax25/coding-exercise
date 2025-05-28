package com.branch.poc.service.impl;

import com.branch.poc.exception.ApiException;
import com.branch.poc.response.UserDto;
import com.branch.poc.service.rest.GitHubService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.branch.poc.constants.TestConstants.EXPECTED_USER;
import static com.branch.poc.constants.TestConstants.GITHUB_USER;
import static com.branch.poc.constants.TestConstants.GITHUB_USER_REPOS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private GitHubService gitHubService;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testGetUser () throws ApiException {
        when(gitHubService.getUser("testUser")).thenReturn(GITHUB_USER);
        when(gitHubService.getRepositoriesForUser("testUser")).thenReturn(GITHUB_USER_REPOS);
        UserDto actualUserDto = userService.getUser("testUser");
        assertThat(actualUserDto, equalTo(EXPECTED_USER));
    }
}
