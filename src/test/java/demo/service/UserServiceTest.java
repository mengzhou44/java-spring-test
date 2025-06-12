package demo.service;

import demo.entities.User;
import demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testGetUserByName() {
        User user = new User(1L, "Charlie");
        when(userRepository.findByName("Charlie")).thenReturn(Optional.of(user));

        Optional<User> result = userService.getUserByName("Charlie");

        assertTrue(result.isPresent());
        assertEquals("Charlie", result.get().getName());
    }
}
