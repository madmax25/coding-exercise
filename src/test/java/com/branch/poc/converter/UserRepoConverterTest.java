package com.branch.poc.converter;

import com.branch.poc.response.UserRepoDto;
import com.branch.poc.service.rest.response.GitHubRepoDto;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static com.branch.poc.constants.TestConstants.GITHUB_USER_REPOS;
import static com.branch.poc.constants.TestConstants.REPO_NAME1;
import static com.branch.poc.constants.TestConstants.REPO_URL1;
import static com.branch.poc.constants.TestConstants.USER_REPO_DTO1;
import static com.branch.poc.constants.TestConstants.USER_REPO_DTO2;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

public class UserRepoConverterTest {

    @Test
    public void testConvert() {
        UserRepoDto actualUserRepoDto =
                UserRepoConverter.of(new GitHubRepoDto(REPO_NAME1, REPO_URL1));

        assertThat(actualUserRepoDto.repoName(), equalTo(REPO_NAME1));
        assertThat(actualUserRepoDto.repoUrl(), equalTo(REPO_URL1));
    }

    @Test
    public void testConvert_null() {
        UserRepoDto actualUserRepoDto =
                UserRepoConverter.of((GitHubRepoDto) null);

        assertThat(actualUserRepoDto, is(nullValue()));
    }

    @Test
    public void testConvertSet() {
        Set<UserRepoDto> actualUserRepos =
                UserRepoConverter.of(GITHUB_USER_REPOS);

        assertThat(actualUserRepos, containsInAnyOrder(USER_REPO_DTO1, USER_REPO_DTO2));
    }
}
