package service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import vk.app.authorization.AuthorizationService;
import vk.app.authorization.dto.requests.RegistrationRequest;
import vk.app.authorization.dto.responses.AuthorizationResponse;
import vk.app.authorization.userInfo.AccessRoles;
import vk.app.configuration.WebTokenService;
import vk.app.repository.UserRepository;
import vk.app.user.UserEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthorizationServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private WebTokenService webTokenService;
    @InjectMocks
    private AuthorizationService authorizationService;

    @Test
    void register() {
        RegistrationRequest registrationRequest =
                new RegistrationRequest("John", "Doe", "john@example.com", "password", AccessRoles.USER);

        UserEntity userEntity = UserEntity.builder()
                .name("John")
                .surname("Doe")
                .email("john@example.com")
                .password("encodedPassword")
                .accessType(AccessRoles.USER)
                .build();

        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        when(webTokenService.generateToken(userEntity)).thenReturn("jwtToken");

        AuthorizationResponse response = authorizationService.register(registrationRequest);

        assertEquals("jwtToken", response.getAccessToken());
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

}
