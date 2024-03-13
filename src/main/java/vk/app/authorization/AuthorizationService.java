package vk.app.authorization;

import org.springframework.security.core.Authentication;
import vk.app.authorization.dto.requests.AuthorizationRequest;
import vk.app.authorization.dto.requests.RegistrationRequest;
import vk.app.authorization.dto.responses.AuthorizationResponse;
import vk.app.configuration.WebTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vk.app.repository.UserRepository;
import vk.app.user.UserEntity;

import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class AuthorizationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final WebTokenService webTokenService;
    private final AuthenticationManager authenticationManager;

    public AuthorizationResponse register(RegistrationRequest request) {
        Supplier<UserEntity> createUser = () -> {
            UserEntity user = UserEntity.builder()
                    .name(request.getFirstname()).surname(request.getLastname())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .accessType(request.getRole())
                    .build();
            return userRepository.save(user);
        };

        UserEntity savedUser = createUser.get();
        String jwtToken = webTokenService.generateToken(savedUser);
        return AuthorizationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    public AuthorizationResponse authenticate(AuthorizationRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        String userEmail =  authentication.getName();
        UserEntity user = userRepository.findUserEntityByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
        String jwtToken = webTokenService.generateToken(user);
        return AuthorizationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

}
