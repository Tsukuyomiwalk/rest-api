package service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.annotation.DirtiesContext;
import vk.app.clients.WebClientAlbums;
import vk.app.controllers.dto.responses.AddAlbumsResponse;
import vk.app.controllers.dto.responses.GetAlbumsResponse;
import vk.app.controllers.dto.responses.UpdAlbumsResponse;
import vk.app.services.AlbumsService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ServiceAlbumTest {

    @Mock
    private WebClientAlbums webClientAlbums;

    @InjectMocks
    private AlbumsService albumsService;

    @Test
    void testGetAlbumById() {
        final int albumId = 1;
        GetAlbumsResponse albumResponse = new GetAlbumsResponse(albumId, 1,"Name");
        when(webClientAlbums.getAlbumsById(albumId)).thenReturn(albumResponse);

        GetAlbumsResponse album = albumsService.getAlbumById(albumId);

        assertThat(album).isNotNull();

        verify(webClientAlbums, times(1)).getAlbumsById(albumId);
    }

    @Test
    void testGetAlbums() {
        GetAlbumsResponse[] expectedResponse = {new GetAlbumsResponse(1, 1, "Name")};
        when(webClientAlbums.getAlbums()).thenReturn(expectedResponse);

        GetAlbumsResponse[] result = albumsService.getAlbums();

        assertThat(result).isEqualTo(expectedResponse);
        verify(webClientAlbums, times(1)).getAlbums();
    }

    @Test
    void testAddAlbum() {
        String name = "Name";
        Integer id = 1;
        AddAlbumsResponse expectedResponse = new AddAlbumsResponse(1);
        when(webClientAlbums.addAlbum(id, name)).thenReturn(expectedResponse);

        AddAlbumsResponse result = albumsService.addAlbum(name, id);

        assertThat(result).isEqualTo(expectedResponse);
        verify(webClientAlbums, times(1)).addAlbum(id, name);
    }

    @Test
    void testUpdAlbum() {
        String name = "Name";
        Integer id = 1;
        UpdAlbumsResponse expectedResponse = new UpdAlbumsResponse(id);
        when(webClientAlbums.updAlbum(name, id)).thenReturn(expectedResponse);

        UpdAlbumsResponse result = albumsService.updAlbum(name, id);

        assertThat(result).isEqualTo(expectedResponse);
        verify(webClientAlbums, times(1)).updAlbum(name, id);
    }

    @Test
    void testDeleteAlbumById() {
        Integer id = 1;
        albumsService.deleteAlbumById(id);
        verify(webClientAlbums, times(1)).deleteAlbum(id);
    }
}
