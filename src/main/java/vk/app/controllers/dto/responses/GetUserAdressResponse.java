package vk.app.controllers.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetUserAdressResponse {
    String street;
    String suite;
    String city;
    String zipcode;
    GetGeoResponse geo;
}
