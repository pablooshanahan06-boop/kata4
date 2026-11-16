package software.ulpgc.katas.infrastructure.server;

import io.javalin.http.Context;

public class HolaEndPoint {
    public void handle(Context ctx) {
        String name = ctx.queryParam("name");
        if (name == null || name.isBlank()) name = "Mundo";
        ctx.result("Hola " + name + " desde Javalin!");
    }
}