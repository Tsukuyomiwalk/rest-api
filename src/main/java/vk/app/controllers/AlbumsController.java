package vk.app.controllers;

import vk.app.controllers.dto.requests.AddPostsRequest;
import vk.app.controllers.dto.requests.UpdPostsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vk.app.services.AlbumsService;
import org.springframework.security.access.prepost.PreAuthorize;
import vk.app.controllers.dto.responses.AddAlbumsResponse;
import vk.app.controllers.dto.responses.GetAlbumsResponse;
import vk.app.controllers.dto.responses.UpdAlbumsResponse;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ALBUMS', 'ADMIN')")
public class AlbumsController {
    private final AlbumsService albumsService;

    @GetMapping("/api/posts/")
    @PreAuthorize("hasAnyAuthority('admin:read', 'albums:read')")
    public GetAlbumsResponse[] getAlbums() {
        return albumsService.getAlbums();
    }

    @GetMapping("/api/posts/{id}")
    @PreAuthorize("hasAnyAuthority('admin:read', 'albums:read')")

    public GetAlbumsResponse getPostById(@PathVariable Integer id) {
        return albumsService.getAlbumById(id);
    }

    @PostMapping("/api/posts/")
    @PreAuthorize("hasAnyAuthority('admin:create')")

    public AddAlbumsResponse addPost(@RequestBody AddPostsRequest request) {
        return albumsService.addAlbum(request.getTitle(), request.getUserId());
    }

    @PutMapping("/api/posts/{id}")
    @PreAuthorize("hasAuthority('admin:update')")
    public UpdAlbumsResponse updPost(@PathVariable Integer id, @RequestBody UpdPostsRequest request) {
        return albumsService.updAlbum(request.getTitle(), id);
    }

    @DeleteMapping("/api/posts/{id}")
    @PreAuthorize("hasAuthority('admin:delete')")
    public void deletePostById(@PathVariable Integer id) {
        albumsService.deleteAlbumById(id);
    }
}
