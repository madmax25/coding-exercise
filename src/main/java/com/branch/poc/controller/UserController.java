package com.branch.poc.controller;

import com.branch.poc.exception.ApiException;
import com.branch.poc.response.UserDto;
import com.branch.poc.service.UserService;
import jakarta.validation.constraints.Pattern;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@Validated
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{userName}")
    public UserDto getUser(@PathVariable @Pattern(regexp = "^[a-zA-Z0-9-]+$") String userName) throws ApiException {
        return userService.getUser(userName);
    }
}
