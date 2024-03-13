package vk.app.controllers.dto.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import vk.app.controllers.dto.responses.GetUserWorkResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddUsersRequest {
    String name;
    String email;
    String phone;
    GetUserWorkResponse work;

    @JsonCreator
    public AddUsersRequest(@JsonProperty("name") String name,
                           @JsonProperty("email") String email,
                           @JsonProperty("phone") String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
}
