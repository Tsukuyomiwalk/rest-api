package vk.app.clients;


import vk.app.controllers.dto.requests.*;
import vk.app.controllers.dto.responses.GetUserWorkResponse;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

public abstract class AbstractWebClient {

    protected <L> L getInfo(Class<L> link, String uri) {
        return getWebClient().get()
                .uri(uri).accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(
                        status -> status.is4xxClientError() || status.is5xxServerError(),
                        this::handleErrorResponse
                )
                .bodyToMono(link).block();
    }

    //:TODO вынести общее
    protected <L> L postInfo(Class<L> link, String uri, String jsonBody, Integer id, String name) {
        return getWebClient().post()
                .uri(uri).body(BodyInserters.fromValue(new AddPostsRequest(name, jsonBody, id)))
                .retrieve()
                .onStatus(
                        status -> status.is4xxClientError() || status.is5xxServerError(),
                        this::handleErrorResponse
                )
                .bodyToMono(link).block();
    }

    protected <L> L postInfo(Class<L> link, String uri, String title, Integer id) {
        return getWebClient().post()
                .uri(uri).body(BodyInserters.fromValue(new AddAlbumsRequest(id, title)))
                .retrieve()
                .onStatus(
                        status -> status.is4xxClientError() || status.is5xxServerError(),
                        this::handleErrorResponse
                )
                .bodyToMono(link).block();
    }


    protected <L> L postInfo(Class<L> link, String uri, String name, String email, String phone, GetUserWorkResponse work) {
        return getWebClient().post()
                .uri(uri).body(BodyInserters.fromValue(new AddUsersRequest(name, email, phone, work)))
                .retrieve()
                .onStatus(
                        status -> status.is4xxClientError() || status.is5xxServerError(),
                        this::handleErrorResponse
                )
                .bodyToMono(link).block();
    }

    protected <L> L updInfo(Class<L> link, String uri, String name, String jsonBody, Integer id) {
        return getWebClient().put()
                .uri(uri + '/' + id.toString()).bodyValue(BodyInserters.fromValue(new UpdPostsRequest(name, jsonBody)))
                .retrieve()
                .onStatus(
                        status -> status.is4xxClientError() || status.is5xxServerError(),
                        this::handleErrorResponse
                )
                .bodyToMono(link).block();
    }

    protected <L> L updInfo(Class<L> link, String uri, String name, Integer id) {
        return getWebClient().put()
                .uri(uri + '/' + id.toString()).bodyValue(BodyInserters.fromValue(new UpdAlbumsRequest(name)))
                .retrieve()
                .onStatus(
                        status -> status.is4xxClientError() || status.is5xxServerError(),
                        this::handleErrorResponse
                )
                .bodyToMono(link).block();
    }

    protected <L> void deleteInfo(Class<L> link, String uri) {
        getWebClient().delete()
                .uri(uri)
                .retrieve()
                .onStatus(
                        status -> status.is4xxClientError() || status.is5xxServerError(),
                        this::handleErrorResponse
                )
                .bodyToMono(link).block();
    }

    protected Mono<? extends Throwable> handleErrorResponse(ClientResponse response) {
        if (response.statusCode().is4xxClientError()) {
            return Mono.error(new ResponseStatusException(
                    response.statusCode(), "Client error: " + response.statusCode()));
        } else if (response.statusCode().is5xxServerError()) {
            return Mono.error(new ResponseStatusException(
                    response.statusCode(), "Server error: " + response.statusCode()));
        }
        return Mono.error(new WebClientResponseException(
                "Unknown error", response.statusCode().value(), response.toString(), null, null, null));
    }

    protected abstract WebClient getWebClient();

}
