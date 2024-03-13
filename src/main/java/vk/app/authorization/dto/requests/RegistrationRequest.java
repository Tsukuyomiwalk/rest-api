package vk.app.authorization.dto.requests;

import vk.app.authorization.userInfo.AccessRoles;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationRequest {
    String firstname;
    String lastname;
    String email;
    String password;
    AccessRoles role;
}
