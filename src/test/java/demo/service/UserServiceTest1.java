package demo.service;

import demo.entities.User;
import demo.repository.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserServiceTest1 {

    private UserRepository userRepository = mock(UserRepository.class);
    private UserService userService = new UserService(userRepository);

    @Test
    void testGetUserByName() {
        User user = new User(1L, "Charlie");
        when(userRepository.findByName("Charlie")).thenReturn(Optional.of(user));

        Optional<User> result = userService.getUserByName("Charlie");

        assertTrue(result.isPresent());
        assertEquals("Charlie", result.get().getName());
    }
}