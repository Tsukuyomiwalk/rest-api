package vk.app.controllers.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetPostsResponse {
    Integer id;
    String title;
    @JsonProperty
    String body;
    Integer userId;
}
