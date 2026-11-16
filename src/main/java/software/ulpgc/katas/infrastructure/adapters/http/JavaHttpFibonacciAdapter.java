package software.ulpgc.katas.infrastructure.adapters.http;

import software.ulpgc.katas.application.domain.FibonacciSeries;
import software.ulpgc.katas.application.ports.FibonacciServicePort;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class JavaHttpFibonacciAdapter implements FibonacciServicePort {
    private final HttpClient client = HttpClient.newHttpClient();
    private final JsonFibonacciParser parser = new JsonFibonacciParser();

    @Override
    public FibonacciSeries fetchSeries(int count) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/fibonacci?count=" + count))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return parser.parse(response.body());
        } catch (Exception e) {
            throw new RuntimeException("Fallo en peticion Fibonacci: " + e.getMessage());
        }
    }
}