package vk.app.controllers.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetAlbumsResponse {
    Integer userId;
    Integer id;
    String title;
}
