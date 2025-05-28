package com.branch.poc.controller;

import com.branch.poc.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @MockitoBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Test
    void testGetUser_success() throws Exception {
        mockMvc.perform(get("/v1/users/testUser")
                        .header("Authorization", "Basic YXBpLXVzZXI6YXBpLXBhc3N3b3Jk"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(userService).getUser(anyString());
    }

    @Test
    void testGetUser_invalidInput() throws Exception {
        mockMvc.perform(get("/v1/users/%2B")
                        .header("Authorization", "Basic YXBpLXVzZXI6YXBpLXBhc3N3b3Jk"))
                .andExpect(status().is4xxClientError());
        verify(userService, never()).getUser(anyString());
    }
}