package software.ulpgc.katas.infrastructure.adapters.http;

import software.ulpgc.katas.application.domain.FibonacciSeries;
import software.ulpgc.katas.application.ports.FibonacciServicePort;
import software.ulpgc.katas.application.ports.ServiceCommunicationException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class JavaHttpFibonacciAdapter implements FibonacciServicePort {
    private final String baseUrl;
    private final HttpClient client;
    private final JsonFibonacciParser parser;

    public JavaHttpFibonacciAdapter(String baseUrl) {
        this.baseUrl = baseUrl;
        this.client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(3))
                .build();
        this.parser = new JsonFibonacciParser();
    }

    @Override
    public FibonacciSeries fetchSeries(int count) {
        URI endpoint = URI.create(baseUrl + "/fibonacci?count=" + count);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(endpoint)
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new ServiceCommunicationException(
                        "Error HTTP " + response.statusCode() + " del servidor: " + response.body()
                );
            }

            return parser.parse(response.body());

        } catch (ConnectException e) {
            throw new ServiceCommunicationException("Servicio no disponible en " + baseUrl + ". Asegurate de que Javalin este arrancado.");
        } catch (IOException | InterruptedException e) {
            throw new ServiceCommunicationException("Error de red al comunicarse con el servicio: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new ServiceCommunicationException("Respuesta invalida del servidor: " + e.getMessage());
        }
    }
}