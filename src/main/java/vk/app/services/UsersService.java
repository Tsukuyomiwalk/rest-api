package vk.app.services;


import vk.app.clients.WebClientUsers;
import vk.app.controllers.dto.responses.AddUsersResponse;
import vk.app.controllers.dto.responses.GetUserWorkResponse;
import vk.app.controllers.dto.responses.GetUsersResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final WebClientUsers webClientUser;

    @Cacheable(value = "users", unless = "#result == null")

    public GetUsersResponse getUserById(Integer id) {
        return webClientUser.getUserById(id);
    }

    @Cacheable(value = "users", unless = "#result == null")
    public GetUsersResponse[] getUsers() {
        return webClientUser.getAllUsers();
    }

    public AddUsersResponse addUser(String name, String email, String phone, GetUserWorkResponse work) {
        return webClientUser.addUser(name, email, phone, work);
    }

    public void deleteUserById(Integer id) {
        webClientUser.deletePost(id);
    }
}
