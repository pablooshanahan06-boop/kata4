package software.ulpgc.katas;

import software.ulpgc.katas.application.ports.GreetingServicePort;
import software.ulpgc.katas.infrastructure.adapters.http.JavaHttpGreetingAdapter;

public class ClientMain {
    public static void main(String[] args) {
        GreetingServicePort service = new JavaHttpGreetingAdapter();
        String respuesta = service.fetchGreeting("Pablo");
        System.out.println("Respuesta recibida: " + respuesta);
    }
}