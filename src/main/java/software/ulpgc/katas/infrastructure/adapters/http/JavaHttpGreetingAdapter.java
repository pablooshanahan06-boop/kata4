package software.ulpgc.katas.infrastructure.adapters.http;

import software.ulpgc.katas.application.ports.GreetingServicePort;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class JavaHttpGreetingAdapter implements GreetingServicePort {
    private final HttpClient client = HttpClient.newHttpClient();

    @Override
    public String fetchGreeting(String name) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/hola?name=" + name))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            throw new RuntimeException("Error conectando con el servidor: " + e.getMessage());
        }
    }
}