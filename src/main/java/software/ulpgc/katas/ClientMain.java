package software.ulpgc.katas;

import software.ulpgc.katas.application.domain.FibonacciSeries;
import software.ulpgc.katas.application.ports.FibonacciServicePort;
import software.ulpgc.katas.infrastructure.adapters.http.JavaHttpFibonacciAdapter;

public class ClientMain {
    public static void main(String[] args) {
        FibonacciServicePort service = new JavaHttpFibonacciAdapter();
        FibonacciSeries series = service.fetchSeries(8);
        System.out.println("=== Serie de Fibonacci (" + series.count() + " elementos) ===");
        System.out.println("Valores: " + series.values());
    }
}