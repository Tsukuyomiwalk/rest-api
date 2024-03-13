package vk.app.authorization;

import vk.app.audit.AuditService;
import vk.app.audit.Status;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthorizationReject implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;
    private final AuditService auditService;
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        auditService.saveAudit(Status.REJECTED);
        AuthorizationAccept.proccess(httpServletResponse, objectMapper);
    }

}
