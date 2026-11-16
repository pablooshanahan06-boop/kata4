package software.ulpgc.katas;

import io.javalin.Javalin;
import software.ulpgc.katas.infrastructure.server.FibonacciEndPoint;
import software.ulpgc.katas.infrastructure.server.HolaEndPoint;

public class ServerMain {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(8080);
        app.get("/hola", new HolaEndPoint()::handle);
        app.get("/fibonacci", new FibonacciEndPoint()::handle);
        System.out.println("Servidor Javalin escuchando en http://localhost:8080");
    }
}