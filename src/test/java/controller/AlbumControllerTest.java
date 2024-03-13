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
import vk.app.controllers.AlbumsController;
import vk.app.controllers.dto.requests.AddPostsRequest;
import vk.app.controllers.dto.responses.AddAlbumsResponse;
import vk.app.controllers.dto.responses.GetAlbumsResponse;
import vk.app.services.AlbumsService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class AlbumControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AlbumsService albumsService;

    @InjectMocks
    private AlbumsController albumsController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(albumsController).build();
    }

    @Test
    void testGetAlbums() throws Exception {
        GetAlbumsResponse[] responses = {new GetAlbumsResponse(1, 1, "Title")};
        when(albumsService.getAlbums()).thenReturn(responses);

        mockMvc.perform(get("/api/posts/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Title"))
                .andExpect(jsonPath("$[0].userId").value(1));

        verify(albumsService, times(1)).getAlbums();
    }

    @Test
    void testGetAlbumById() throws Exception {
        int id = 1;
        GetAlbumsResponse response = new GetAlbumsResponse(id, 1, "Title");
        when(albumsService.getAlbumById(id)).thenReturn(response);

        mockMvc.perform(get("/api/posts/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.title").value("Title"))
                .andExpect(jsonPath("$.userId").value(1));

        verify(albumsService, times(1)).getAlbumById(id);
    }

    @Test
    void testAddAlbum() throws Exception {
        AddPostsRequest request = new AddPostsRequest("Title", "Body", 1);
        AddAlbumsResponse response = new AddAlbumsResponse(1);
        when(albumsService.addAlbum(request.getTitle(), request.getUserId())).thenReturn(response);

        mockMvc.perform(post("/api/posts/")
                        .content(new ObjectMapper().writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1));

        verify(albumsService, times(1)).addAlbum(request.getTitle(), request.getUserId());
    }

    @Test
    void testDeleteAlbumById() throws Exception {
        int id = 1;

        mockMvc.perform(delete("/api/posts/{id}", id))
                .andExpect(status().isOk());

        verify(albumsService, times(1)).deleteAlbumById(id);
    }
}
