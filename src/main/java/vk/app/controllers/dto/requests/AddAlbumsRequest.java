package vk.app.controllers.dto.requests;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AddAlbumsRequest {
    Integer id;
    String title;

    @JsonCreator
    public AddAlbumsRequest(@JsonProperty("id") Integer id,
                            @JsonProperty("title") String title) {
        this.title = title;
        this.id = id;
    }
}
