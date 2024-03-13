package service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import vk.app.clients.WebClientPosts;
import vk.app.controllers.dto.responses.AddPostsResponse;
import vk.app.controllers.dto.responses.GetPostsResponse;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.annotation.DirtiesContext;
import vk.app.controllers.dto.responses.UpdPostsResponse;
import vk.app.services.PostsService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ServicePostTest {

    @Mock
    private WebClientPosts webClientPost;

    @InjectMocks
    private PostsService postsService;

    @Test
    void testFindBookById() {
        final int postId = 1;
        GetPostsResponse postResponse = new GetPostsResponse(postId, null, null, null);
        when(webClientPost.getPostById(postId)).thenReturn(postResponse);

        GetPostsResponse post = postsService.getPostById(postId);

        assertThat(post).isNotNull();

        verify(webClientPost, times(1)).getPostById(postId);
    }

    @Test
    void testGetPosts() {
        GetPostsResponse[] expectedResponse = {new GetPostsResponse(1, "Title", "Body", 1)};
        when(webClientPost.getAllPosts()).thenReturn(expectedResponse);

        GetPostsResponse[] result = postsService.getPosts();

        assertThat(result).isEqualTo(expectedResponse);
        verify(webClientPost, times(1)).getAllPosts();
    }

    @Test
    void testAddPost() {
        String name = "Title";
        String jsonBody = "{\"key\":\"value\"}";
        Integer id = 1;
        AddPostsResponse expectedResponse = new AddPostsResponse(1);
        when(webClientPost.addPost(name, id, jsonBody)).thenReturn(expectedResponse);

        AddPostsResponse result = postsService.addPost(name, jsonBody, id);

        assertThat(result).isEqualTo(expectedResponse);
        verify(webClientPost, times(1)).addPost(name, id, jsonBody);
    }

    @Test
    void testUpdPost() {
        String name = "Title";
        String jsonBody = "{\"key\":\"value\"}";
        Integer id = 1;
        UpdPostsResponse expectedResponse = new UpdPostsResponse(id);
        when(webClientPost.updPost(name, jsonBody, id)).thenReturn(expectedResponse);

        UpdPostsResponse result = postsService.updPost(name, jsonBody, id);

        assertThat(result).isEqualTo(expectedResponse);
        verify(webClientPost, times(1)).updPost(name, jsonBody, id);
    }

    @Test
    void testDeletePostById() {
        Integer id = 1;
        postsService.deletePostById(id);
        verify(webClientPost, times(1)).deletePost(id);
    }
}
