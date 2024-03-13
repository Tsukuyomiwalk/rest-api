package vk.app.controllers;

import vk.app.controllers.dto.requests.AddUsersRequest;
import vk.app.controllers.dto.responses.AddUsersResponse;
import vk.app.controllers.dto.responses.GetUsersResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vk.app.services.UsersService;

@RestController
@RequiredArgsConstructor
public class UsersController {
    private final UsersService usersService;

    @GetMapping("/api/users/{id}")
    public GetUsersResponse getUserById(@PathVariable Integer id) {
        return usersService.getUserById(id);
    }

    @GetMapping("api/users")
    public GetUsersResponse[] getUsers() {
        return usersService.getUsers();
    }

    @PostMapping("/api/users")

    public AddUsersResponse addUser(@RequestBody AddUsersRequest addUser) {
        return usersService.addUser(addUser.getName(), addUser.getEmail(), addUser.getPhone(), addUser.getWork());
    }

    @DeleteMapping("/api/users/{id}")
    public void deletePostById(@PathVariable Integer id) {
        usersService.deleteUserById(id);
    }
}
