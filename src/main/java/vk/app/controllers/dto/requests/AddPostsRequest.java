package vk.app.controllers.dto.requests;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddPostsRequest {
    @JsonProperty
    String title;
    @JsonProperty
    String body;
    @JsonProperty
    Integer userId;

    @JsonCreator
    public AddPostsRequest(@JsonProperty("title") String title,
                           @JsonProperty("body") String body,
                           @JsonProperty("userId") int userId) {
        this.title = title;
        this.body = body;
        this.userId = userId;
    }
}
