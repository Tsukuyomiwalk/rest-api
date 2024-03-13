package vk.app.clients;

import vk.app.controllers.dto.responses.AddUsersResponse;
import vk.app.controllers.dto.responses.GetUserWorkResponse;
import vk.app.controllers.dto.responses.GetUsersResponse;
import org.springframework.web.reactive.function.client.WebClient;

public class WebClientUsers extends AbstractWebClient {

    private static final String URL = "https://jsonplaceholder.typicode.com/posts";
    private final WebClient webClient;
    private final String url;

    public WebClientUsers(WebClient webClient, String url) {
        this.webClient = webClient;
        this.url = url;
    }

    public WebClientUsers(WebClient webClient) {
        this.webClient = webClient;
        this.url = URL;
    }

    public GetUsersResponse getUserById(Integer id) {
        return getInfo(GetUsersResponse.class, url + '/' + id);
    }

    public GetUsersResponse[] getAllUsers() {
        return getInfo(GetUsersResponse[].class, url);
    }

    public AddUsersResponse addUser(String name, String email, String phone, GetUserWorkResponse work) {
        return postInfo(AddUsersResponse.class, url, name, email, phone, work);
    }

    public void deletePost(Integer id) {
        deleteInfo(Void.class, url + '/' + id);
    }

    @Override
    protected WebClient getWebClient() {
        return webClient;
    }
}
