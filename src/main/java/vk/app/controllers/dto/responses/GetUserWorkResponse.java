package vk.app.controllers.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetUserWorkResponse {
    String name;
    String position;
}