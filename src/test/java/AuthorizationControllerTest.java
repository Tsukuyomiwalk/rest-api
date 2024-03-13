
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import vk.app.authorization.AuthorizationController;
import vk.app.authorization.AuthorizationService;
import vk.app.authorization.dto.requests.AuthorizationRequest;
import vk.app.authorization.dto.requests.RegistrationRequest;
import vk.app.authorization.dto.responses.AuthorizationResponse;
import vk.app.authorization.userInfo.AccessRoles;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthorizationControllerTest {

    @Mock
    private AuthorizationService authorizationService;

    @Test
    void register() {
        AuthorizationController controller = new AuthorizationController(authorizationService);
        RegistrationRequest registrationRequest = new RegistrationRequest("John", "Doe", "john@example.com", "password", AccessRoles.USER);
        when(authorizationService.register(registrationRequest)).thenReturn(new AuthorizationResponse("token"));

        ResponseEntity<AuthorizationResponse> responseEntity = controller.register(registrationRequest);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("token", Objects.requireNonNull(responseEntity.getBody()).getAccessToken());
        verify(authorizationService, times(1)).register(registrationRequest);
    }

    @Test
    void authenticate() {
        AuthorizationController controller = new AuthorizationController(authorizationService);
        AuthorizationRequest authorizationRequest = new AuthorizationRequest("john@example.com", "password", AccessRoles.USER);
        when(authorizationService.authenticate(authorizationRequest)).thenReturn(new AuthorizationResponse("token"));

        ResponseEntity<AuthorizationResponse> responseEntity = controller.authenticate(authorizationRequest);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("token", Objects.requireNonNull(responseEntity.getBody()).getAccessToken());
        verify(authorizationService, times(1)).authenticate(authorizationRequest);
    }
}
