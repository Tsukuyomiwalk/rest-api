package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import vk.app.controllers.UsersController;
import vk.app.controllers.dto.requests.AddUsersRequest;
import vk.app.controllers.dto.responses.*;
import vk.app.services.UsersService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UsersService usersService;

    @InjectMocks
    private UsersController usersController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(usersController).build();
    }

    @Test
    void testGetUserById() throws Exception {
        int id = 1;
        GetGeoResponse geoResponse = new GetGeoResponse("1233", "ru");
        GetUserAdressResponse address = new GetUserAdressResponse("Nevskiy", "suiteValue", "cityValue", "zipcodeValue", geoResponse);

        GetUsersResponse response = new GetUsersResponse(id, "Name", "Email", address, "93274982378");
        when(usersService.getUserById(id)).thenReturn(response);

        mockMvc.perform(get("/api/users/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value("Name"))
                .andExpect(jsonPath("$.email").value("Email"))
                .andExpect(jsonPath("$.phone").value("93274982378"));
        verify(usersService, times(1)).getUserById(id);
    }

    @Test
    void testAddUser() throws Exception {
        AddUsersRequest request = new AddUsersRequest("Name", "Email", "93274982378");
        AddUsersResponse response = new AddUsersResponse(1);
        when(usersService.addUser(request.getName(), request.getEmail(), request.getPhone(), request.getWork())).thenReturn(response);

        mockMvc.perform(post("/api/users")
                        .content(new ObjectMapper().writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1));

        verify(usersService, times(1)).addUser(request.getName(), request.getEmail(), request.getPhone(), request.getWork());
    }

    @Test
    void testDeleteUserById() throws Exception {
        int id = 1;

        mockMvc.perform(delete("/api/users/{id}", id))
                .andExpect(status().isOk());

        verify(usersService, times(1)).deleteUserById(id);
    }
}
