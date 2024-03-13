package vk.app.services;

import vk.app.clients.WebClientAlbums;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import vk.app.controllers.dto.responses.AddAlbumsResponse;
import vk.app.controllers.dto.responses.GetAlbumsResponse;
import vk.app.controllers.dto.responses.UpdAlbumsResponse;

@Service
@RequiredArgsConstructor
public class AlbumsService {
    private final WebClientAlbums webClientAlbums;

    @Cacheable(value = "albums", unless = "#result == null")

    public GetAlbumsResponse[] getAlbums() {
        return webClientAlbums.getAlbums();
    }

    @Cacheable(value = "albums", unless = "#result == null")

    public GetAlbumsResponse getAlbumById(Integer id) {
        return webClientAlbums.getAlbumsById(id);
    }

    public AddAlbumsResponse addAlbum(String name, Integer id) {
        return webClientAlbums.addAlbum(id, name);
    }

    public UpdAlbumsResponse updAlbum(String name, Integer id) {
        return webClientAlbums.updAlbum(name, id);
    }

    public void deleteAlbumById(Integer id) {
        webClientAlbums.deleteAlbum(id);
    }
}
