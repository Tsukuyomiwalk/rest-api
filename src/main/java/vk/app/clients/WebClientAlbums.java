package vk.app.clients;

import org.springframework.web.reactive.function.client.WebClient;
import vk.app.controllers.dto.responses.AddAlbumsResponse;
import vk.app.controllers.dto.responses.GetAlbumsResponse;
import vk.app.controllers.dto.responses.UpdAlbumsResponse;



public class WebClientAlbums extends AbstractWebClient {
    private static final String URL = "https://jsonplaceholder.typicode.com/posts";

    private final WebClient webClient;
    private final String url;

    public WebClientAlbums(WebClient webClient, String url) {
        this.webClient = webClient;
        this.url = url;
    }

    public WebClientAlbums(WebClient webClient) {
        this.webClient = webClient;
        this.url = URL;

    }

    public GetAlbumsResponse[] getAlbums() {
        return getInfo(GetAlbumsResponse[].class, url);
    }

    public GetAlbumsResponse getAlbumsById(Integer id) {
        return getInfo(GetAlbumsResponse.class, url + '/' + id);
    }

    public AddAlbumsResponse addAlbum(Integer id, String title) {
        return postInfo(AddAlbumsResponse.class, url, title, id);
    }


    public UpdAlbumsResponse updAlbum(String name, Integer id) {
        return updInfo(UpdAlbumsResponse.class, url, name, id);
    }

    public void deleteAlbum(Integer id) {
        deleteInfo(Void.class, url + '/' + id);
    }

    @Override
    protected WebClient getWebClient() {
        return webClient;
    }
}
