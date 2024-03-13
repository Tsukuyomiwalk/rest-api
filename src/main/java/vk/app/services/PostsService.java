package vk.app.services;

import vk.app.clients.WebClientPosts;
import vk.app.controllers.dto.responses.AddPostsResponse;
import vk.app.controllers.dto.responses.GetPostsResponse;
import vk.app.controllers.dto.responses.UpdPostsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostsService {
    private final WebClientPosts webClientPost;

    @Cacheable(value = "posts", unless = "#result == null")

    public GetPostsResponse[] getPosts() {
        return webClientPost.getAllPosts();
    }

    @Cacheable(value = "posts", unless = "#result == null")
    public GetPostsResponse getPostById(Integer id) {
        return webClientPost.getPostById(id);
    }

    public AddPostsResponse addPost(String name, String JsonBody, Integer id) {
        return webClientPost.addPost(name, id, JsonBody);
    }

    public UpdPostsResponse updPost(String name, String JsonBody, Integer id) {
        return webClientPost.updPost(name, JsonBody, id);
    }

    public void deletePostById(Integer id) {
        webClientPost.deletePost(id);
    }
}
