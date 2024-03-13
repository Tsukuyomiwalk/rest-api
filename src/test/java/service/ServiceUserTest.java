package service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.annotation.DirtiesContext;
import vk.app.clients.WebClientUsers;
import vk.app.controllers.dto.responses.*;
import vk.app.services.UsersService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ServiceUserTest {

    @Mock
    private WebClientUsers webClientUser;

    @InjectMocks
    private UsersService usersService;

    @Test
    void testGetUserById() {
        final int userId = 1;
        GetGeoResponse geoResponse = new GetGeoResponse("1233", "ru");

        GetUserAdressResponse address = new GetUserAdressResponse("Nevskiy", "suiteValue", "cityValue", "zipcodeValue", geoResponse);

        GetUsersResponse userResponse = new GetUsersResponse(userId, "Name", "Email", address, "379847r9347");
        when(webClientUser.getUserById(userId)).thenReturn(userResponse);

        GetUsersResponse user = usersService.getUserById(userId);

        assertThat(user).isNotNull();

        verify(webClientUser, times(1)).getUserById(userId);
    }

    @Test
    void testGetUsers() {

        GetGeoResponse geoResponse = new GetGeoResponse("1233", "ru");

        GetUserAdressResponse address = new GetUserAdressResponse("Nevskiy", "suiteValue", "cityValue", "zipcodeValue", geoResponse);

        GetUsersResponse[] expectedResponse = {new GetUsersResponse(1, "Name", "Email", address, "598759749")};
        when(webClientUser.getAllUsers()).thenReturn(expectedResponse);

        GetUsersResponse[] result = usersService.getUsers();

        assertThat(result).isEqualTo(expectedResponse);
        verify(webClientUser, times(1)).getAllUsers();
    }

    @Test
    void testAddUser() {
        String name = "Name";
        String email = "Email";
        String phone = "Phone";
        GetUserWorkResponse work = new GetUserWorkResponse("Work", "Position");
        AddUsersResponse expectedResponse = new AddUsersResponse(1);
        when(webClientUser.addUser(name, email, phone, work)).thenReturn(expectedResponse);

        AddUsersResponse result = usersService.addUser(name, email, phone, work);

        assertThat(result).isEqualTo(expectedResponse);
        verify(webClientUser, times(1)).addUser(name, email, phone, work);
    }

    @Test
    void testDeleteUserById() {
        Integer id = 1;
        usersService.deleteUserById(id);
        verify(webClientUser, times(1)).deletePost(id);
    }
}
