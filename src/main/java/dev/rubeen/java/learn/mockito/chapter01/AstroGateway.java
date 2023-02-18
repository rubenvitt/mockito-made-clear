package dev.rubeen.java.learn.mockito.chapter01;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AstroGateway implements Gateway<AstroResponse> {
    @Override
    public AstroResponse get () {
        return HttpClient.newBuilder()
            .build()
            .sendAsync(
                HttpRequest.newBuilder()
                    .uri(URI.create("http://api.open-notify.org/astros.json"))
                    .build(),
                HttpResponse.BodyHandlers.ofString()
            )
            .thenApply(HttpResponse::body)
            .thenApply(AstroResponse::fromJSON)
            .join();
    }
}
