
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import vk.app.audit.AuditService;
import vk.app.audit.Status;
import vk.app.authorization.AuthorizationAccept;
import vk.app.authorization.AuthorizationReject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class AuditTest {

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private AuditService auditService;

    @Test
    void commence1() throws IOException {
        AuthorizationAccept authorizationAccept = new AuthorizationAccept(objectMapper, auditService);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = new MockHttpServletResponse();
        AuthenticationException authException = mock(AuthenticationException.class);

        authorizationAccept.commence(request, response, authException);

        assertEquals(HttpStatus.UNAUTHORIZED.value(), response.getStatus());
        verify(auditService, times(1)).saveAudit(Status.APPROVED);
    }


    @Test
    void commence2() throws IOException {
        AuthorizationReject authorizationReject = new AuthorizationReject(objectMapper, auditService);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = new MockHttpServletResponse();
        AuthenticationException authException = mock(AuthenticationException.class);

        authorizationReject.commence(request, response, authException);

        assertEquals(HttpStatus.UNAUTHORIZED.value(), response.getStatus());
        verify(auditService, times(1)).saveAudit(Status.REJECTED);
    }
}


