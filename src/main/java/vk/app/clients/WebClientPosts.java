package vk.app.clients;

import vk.app.controllers.dto.responses.AddPostsResponse;
import vk.app.controllers.dto.responses.GetPostsResponse;
import vk.app.controllers.dto.responses.UpdPostsResponse;

import org.springframework.web.reactive.function.client.WebClient;


public class WebClientPosts extends AbstractWebClient {

    private static final String URL = "https://jsonplaceholder.typicode.com/posts";


    private final WebClient webClient;
    private final String url;

    public WebClientPosts(WebClient webClient, String url) {
        this.webClient = webClient;
        this.url = url;
    }

    public WebClientPosts(WebClient webClient) {
        this.webClient = webClient;
        this.url = URL;

    }

    public GetPostsResponse[] getAllPosts() {
        return getInfo(GetPostsResponse[].class, url);
    }

    public GetPostsResponse getPostById(Integer id) {
        return getInfo(GetPostsResponse.class, url + '/' + id);
    }

    public AddPostsResponse addPost(String jsonBody, Integer id, String name) {
        return postInfo(AddPostsResponse.class, url, jsonBody, id, name);
    }


    public UpdPostsResponse updPost(String name, String jsonBody, Integer id) {
        return updInfo(UpdPostsResponse.class, url, jsonBody, name, id);
    }

    public void deletePost(Integer id) {
        deleteInfo(Void.class, url + '/' + id);
    }

    @Override
    protected WebClient getWebClient() {
        return webClient;
    }
}
