package vk.app.authorization;

import org.springframework.context.annotation.Bean;
import vk.app.audit.AuditService;
import vk.app.audit.Status;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AuthorizationAccept extends HttpFilter implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;

    private final AuditService auditService;


    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        auditService.saveAudit(Status.APPROVED);
        proccess(httpServletResponse, objectMapper);
    }

    static void proccess(HttpServletResponse httpServletResponse, ObjectMapper objectMapper) throws IOException {
        httpServletResponse.setContentType("application/json");
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("timestamp", System.currentTimeMillis());
        responseBody.put("status", HttpStatus.UNAUTHORIZED.value());
        responseBody.put("error", "Unauthorized");
        responseBody.put("message", "Access Denied");
        objectMapper.writeValue(httpServletResponse.getWriter(), responseBody);
    }


}
