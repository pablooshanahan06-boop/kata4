package software.ulpgc.katas.infrastructure.adapters.http;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import software.ulpgc.katas.application.domain.FibonacciSeries;

public class JsonFibonacciParser {
    private final Gson gson = new Gson();

    public FibonacciSeries parse(String json) {
        try {
            FibonacciSeries series = gson.fromJson(json, FibonacciSeries.class);
            if (series == null || series.values() == null) {
                throw new IllegalArgumentException("Respuesta JSON vacia o incompleta");
            }
            return series;
        } catch (JsonSyntaxException e) {
            throw new IllegalArgumentException("Formato JSON invalido recibido del servidor", e);
        }
    }
}