package vk.app.authorization.dto.requests;


import lombok.AllArgsConstructor;
import lombok.Data;
import vk.app.authorization.userInfo.AccessRoles;

@Data
@AllArgsConstructor
public class AuthorizationRequest {
    String email;
    String password;
    AccessRoles role;
}
