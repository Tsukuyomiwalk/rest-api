package vk.app.audit;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import vk.app.repository.AuditRepository;
import vk.app.user.AuditEntity;
import vk.app.user.UserEntity;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuditService {

    private final AuditRepository auditRepository;

    public void saveAudit(Status status) {
        String userEmail = getUserEmailFromAuthentication();
        String url = getRequestUrl();
        String request = getRequestMethod();
        AuditEntity audit = new AuditEntity(status, userEmail, LocalDateTime.now(), url, request);
        auditRepository.save(audit);
    }

    private String getUserEmailFromAuthentication() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .filter(Authentication::isAuthenticated)
                .filter(authentication -> !(authentication instanceof AnonymousAuthenticationToken))
                .map(Authentication::getPrincipal)
                .map(UserEntity.class::cast)
                .map(UserEntity::getEmail)
                .orElse("-");
    }

    private String getRequestUrl() {
        return Optional.ofNullable(RequestContextHolder.getRequestAttributes())
                .map(requestAttributes -> (ServletRequestAttributes) requestAttributes)
                .map(ServletRequestAttributes::getRequest)
                .map(HttpServletRequest::getRequestURI)
                .orElse(null);
    }

    private String getRequestMethod() {
        return Optional.ofNullable(RequestContextHolder.getRequestAttributes())
                .map(requestAttributes -> (ServletRequestAttributes) requestAttributes)
                .map(ServletRequestAttributes::getRequest)
                .map(HttpServletRequest::getMethod)
                .orElse(null);
    }
}
