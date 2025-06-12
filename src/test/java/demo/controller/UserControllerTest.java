package demo.controller;

import demo.service.UserService;
import demo.entities.User;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


import static org.mockito.Mockito.when;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void testGetUserFound() throws Exception {
        when(userService.getUserByName("Bob"))
                .thenReturn(Optional.of(new User(1L, "Bob")));

        mockMvc.perform(get("/users/Bob"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Bob"));
    }

    @Test
    void testGetUserNotFound() throws Exception {
        when(userService.getUserByName("Unknown")).thenReturn(Optional.empty());

        mockMvc.perform(get("/users/Unknown"))
                .andExpect(status().isNotFound());
    }
}
