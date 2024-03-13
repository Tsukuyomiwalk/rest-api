package vk.app.controllers.dto.requests;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class UpdAlbumsRequest {
    @JsonProperty
    String title;

    @JsonCreator
    public  UpdAlbumsRequest(@JsonProperty("title") String title) {
        this.title = title;
    }
}
