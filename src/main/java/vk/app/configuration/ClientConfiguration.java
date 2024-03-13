package vk.app.configuration;


import vk.app.clients.WebClientAlbums;
import vk.app.clients.WebClientPosts;
import vk.app.clients.WebClientUsers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;



@Configuration
public class ClientConfiguration {

    @Bean
    public WebClient webClient() {
        HttpClient httpClient = HttpClient.create()
                .compress(true);
        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }


    @Bean
    public WebClientPosts webClientPost(WebClient webClient, @Value("${app.posts.url}") String url) {
        return new WebClientPosts(webClient, url);
    }
    @Bean
    public WebClientAlbums webClientAlbums(WebClient webClient, @Value("${app.albums.url}") String url) {
        return new WebClientAlbums(webClient, url);
    }

    @Bean
    public WebClientUsers webClientUser(WebClient webClient, @Value("${app.users.url}") String url) {
        return new WebClientUsers(webClient, url);
    }
}
