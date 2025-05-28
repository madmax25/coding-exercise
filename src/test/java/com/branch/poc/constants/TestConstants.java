package com.branch.poc.constants;

import com.branch.poc.response.UserDto;
import com.branch.poc.response.UserRepoDto;
import com.branch.poc.service.rest.response.GitHubRepoDto;
import com.branch.poc.service.rest.response.GitHubUserDto;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;

public class TestConstants {
    private TestConstants() {
    }

    public static final String USER_NAME = "userName";
    public static final String DISPLAY_NAME = "displayName";
    public static final String AVATAR_URL = "https://avatarUrl";
    public static final String LOCATION_NAME = "locationName";
    public static final String EMAIL_ADDRESS = "emailAddress";
    public static final String MAIN_GITHUB_URL = "mainGitHubUrl";

    public static final String REPO_NAME1 = "repoName1";
    public static final String REPO_URL1 = "repoName1";
    public static final String REPO_NAME2 = "repoName2";
    public static final String REPO_URL2 = "repoName2";

    public static final LocalDateTime LOCAL_DATE_TIME =
            LocalDateTime.of(2025, 5, 28, 13, 10);

    public static final ZonedDateTime ZONED_DATE_TIME =
            ZonedDateTime.of(LOCAL_DATE_TIME, ZoneId.of("UTC"));

    public static final GitHubUserDto GITHUB_USER =
            new GitHubUserDto(USER_NAME,
                    DISPLAY_NAME,
                    AVATAR_URL,
                    LOCATION_NAME,
                    EMAIL_ADDRESS,
                    MAIN_GITHUB_URL,
                    ZONED_DATE_TIME);

    public static final GitHubRepoDto GITHUB_REPO_DTO1 =
            new GitHubRepoDto(REPO_NAME1, REPO_URL1);

    public static final GitHubRepoDto GITHUB_REPO_DTO2 =
            new GitHubRepoDto(REPO_NAME2, REPO_URL2);

    public static final Set<GitHubRepoDto> GITHUB_USER_REPOS =
            Set.of(GITHUB_REPO_DTO1, GITHUB_REPO_DTO2);

    public static final UserRepoDto USER_REPO_DTO1 =
            new UserRepoDto(REPO_NAME1, REPO_URL1);

    public static final UserRepoDto USER_REPO_DTO2 =
            new UserRepoDto(REPO_NAME2, REPO_URL2);

    public static final Set<UserRepoDto> EXPECTED_USER_REPOS =
            Set.of(USER_REPO_DTO1, USER_REPO_DTO2);

    public static final UserDto EXPECTED_USER =
            new UserDto(USER_NAME,
                    DISPLAY_NAME,
                    AVATAR_URL,
                    LOCATION_NAME,
                    EMAIL_ADDRESS,
                    MAIN_GITHUB_URL,
                    LOCAL_DATE_TIME,
                    EXPECTED_USER_REPOS);
}
