package com.branch.poc.service;

import com.branch.poc.exception.ApiException;
import com.branch.poc.response.UserDto;

public interface UserService {
    UserDto getUser(String userName) throws ApiException;
}
