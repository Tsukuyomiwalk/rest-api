package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import vk.app.controllers.PostsController;
import vk.app.controllers.dto.requests.AddPostsRequest;
import vk.app.controllers.dto.requests.UpdPostsRequest;
import vk.app.controllers.dto.responses.AddPostsResponse;
import vk.app.controllers.dto.responses.GetPostsResponse;
import vk.app.controllers.dto.responses.UpdPostsResponse;
import vk.app.services.PostsService;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@ExtendWith(MockitoExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class   PostControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PostsService postsService;

    @InjectMocks
    private PostsController postsController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(postsController).build();
    }

    @Test
    void testGetPosts() throws Exception {
        GetPostsResponse[] responses = {new GetPostsResponse(1, "Title", "Body", 1)};
        when(postsService.getPosts()).thenReturn(responses);

        mockMvc.perform(get("/api/posts/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Title"))
                .andExpect(jsonPath("$[0].body").value("Body"))
                .andExpect(jsonPath("$[0].userId").value(1));

        // Verify
        verify(postsService, times(1)).getPosts();
    }

    @Test
    void testGetPostById() throws Exception {
        int id = 1;
        GetPostsResponse response = new GetPostsResponse(id, "Title", "Body", 1);
        when(postsService.getPostById(id)).thenReturn(response);

        mockMvc.perform(get("/api/posts/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.title").value("Title"))
                .andExpect(jsonPath("$.body").value("Body"))
                .andExpect(jsonPath("$.userId").value(1));

        verify(postsService, times(1)).getPostById(id);
    }

    @Test
    void testAddPost() throws Exception {
        AddPostsRequest request = new AddPostsRequest("Title", "Body", 1);
        AddPostsResponse response = new AddPostsResponse(1);
        when(postsService.addPost(request.getTitle(), request.getBody(), request.getUserId())).thenReturn(response);

        mockMvc.perform(post("/api/posts/")
                        .content(new ObjectMapper().writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1));

        verify(postsService, times(1)).addPost(request.getTitle(), request.getBody(), request.getUserId());
    }


    @Test
    void testDeletePostById() throws Exception {
        int id = 1;

        mockMvc.perform(delete("/api/posts/{id}", id))
                .andExpect(status().isOk());
        verify(postsService, times(1)).deletePostById(id);
    }
}
