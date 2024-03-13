package vk.app.controllers.dto.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class UpdPostsRequest {
    @JsonProperty
    String title;
    @JsonProperty
    String body;

    @JsonCreator
    public  UpdPostsRequest(@JsonProperty("title") String title,
                           @JsonProperty("body") String body) {
        this.title = title;
        this.body = body;
    }
}
