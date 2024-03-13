package vk.app.authorization.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class AuthorizationResponse {
    @JsonProperty("access_token")
    String accessToken;
}
