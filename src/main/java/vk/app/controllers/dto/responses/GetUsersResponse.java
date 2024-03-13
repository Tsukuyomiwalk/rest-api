package vk.app.controllers.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetUsersResponse {
    Integer id;
    String name;
    String email;
    GetUserAdressResponse address;
    String phone;
}
