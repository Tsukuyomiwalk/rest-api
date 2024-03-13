package vk.app.authorization;

import vk.app.authorization.dto.requests.AuthorizationRequest;
import vk.app.authorization.dto.requests.RegistrationRequest;
import vk.app.authorization.dto.responses.AuthorizationResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthorizationController {
    private final AuthorizationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthorizationResponse> register(
            @RequestBody RegistrationRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthorizationResponse> authenticate(
            @RequestBody AuthorizationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
