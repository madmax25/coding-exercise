package com.branch.poc.converter;

import com.branch.poc.response.UserDto;
import org.junit.jupiter.api.Test;

import static com.branch.poc.constants.TestConstants.EXPECTED_USER;
import static com.branch.poc.constants.TestConstants.GITHUB_USER;
import static com.branch.poc.constants.TestConstants.GITHUB_USER_REPOS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

public class UserConverterTest {

    @Test
    public void convertUser() {
        UserDto actualUserDto = UserConverter.of(GITHUB_USER, GITHUB_USER_REPOS);
        assertThat(actualUserDto, equalTo(EXPECTED_USER));
    }

    @Test
    public void convertUser_nullUser() {
        UserDto actualUserDto = UserConverter.of(null, GITHUB_USER_REPOS);
        assertThat(actualUserDto, is(nullValue()));
    }

    @Test
    public void convertUser_nullRepo() {
        UserDto actualUserDto = UserConverter.of(GITHUB_USER, null);
        assertThat(actualUserDto, is(notNullValue()));
        assertThat(actualUserDto.userRepos(), is(empty()));
    }
}
