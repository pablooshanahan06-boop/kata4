package software.ulpgc.katas.infrastructure.server;

import com.google.gson.Gson;
import io.javalin.http.Context;
import software.ulpgc.katas.application.domain.FibonacciSeries;
import software.ulpgc.katas.application.usecases.FibonacciCalculationUseCase;

public class FibonacciEndPoint {
    private final FibonacciCalculationUseCase useCase = new FibonacciCalculationUseCase();
    private final Gson gson = new Gson();

    public void handle(Context ctx) {
        String countParam = ctx.queryParam("count");
        try {
            int count = (countParam == null) ? 5 : Integer.parseInt(countParam);
            if (count < 0 || count > 50) {
                ctx.status(400).result("El parametro count debe estar entre 0 y 50");
                return;
            }
            FibonacciSeries series = useCase.calculate(count);
            ctx.contentType("application/json");
            ctx.result(gson.toJson(series));
        } catch (NumberFormatException e) {
            ctx.status(400).result("El parametro count debe ser un numero entero valido");
        }
    }
}