package vk.app.controllers;


import vk.app.controllers.dto.requests.AddPostsRequest;
import vk.app.controllers.dto.requests.UpdPostsRequest;
import vk.app.controllers.dto.responses.AddPostsResponse;
import vk.app.controllers.dto.responses.GetPostsResponse;
import vk.app.controllers.dto.responses.UpdPostsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vk.app.services.PostsService;

@RestController
@RequiredArgsConstructor
public class PostsController {

    private final PostsService postsService;

    @GetMapping("/api/posts/")
    public GetPostsResponse[] getPosts() {
        return postsService.getPosts();
    }

    @GetMapping("/api/posts/{id}")
    public GetPostsResponse getPostById(@PathVariable Integer id) {
        return postsService.getPostById(id);
    }

    @PostMapping("/api/posts/")
    public AddPostsResponse addPost(@RequestBody AddPostsRequest request) {
        return postsService.addPost(request.getTitle(), request.getBody(), request.getUserId());
    }

    @PutMapping("/api/posts/{id}")
    public UpdPostsResponse updPost(@PathVariable Integer id, @RequestBody UpdPostsRequest request) {
        return postsService.updPost(request.getTitle(), request.getTitle(), id);
    }

    @DeleteMapping("/api/posts/{id}")
    public void deletePostById(@PathVariable Integer id) {
        postsService.deletePostById(id);
    }
}
