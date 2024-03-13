package vk.app.controllers.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetGeoResponse {
    String dest;
    String country;
}
