package software.ulpgc.katas;

import software.ulpgc.katas.application.domain.FibonacciSeries;
import software.ulpgc.katas.application.ports.FibonacciServicePort;
import software.ulpgc.katas.application.ports.ServiceCommunicationException;
import software.ulpgc.katas.infrastructure.adapters.http.JavaHttpFibonacciAdapter;
import software.ulpgc.katas.infrastructure.config.ServiceConfig;

public class ClientMain {
    public static void main(String[] args) {
        FibonacciServicePort service = new JavaHttpFibonacciAdapter(ServiceConfig.getBaseUrl());

        try {
            System.out.println("Consultando servicio en: " + ServiceConfig.getBaseUrl());
            FibonacciSeries series = service.fetchSeries(10);
            System.out.println("Exito -> Serie de " + series.count() + " elementos: " + series.values());
        } catch (ServiceCommunicationException e) {
            System.err.println("[AVISO CONTROLADO] " + e.getMessage());
        }
    }
}