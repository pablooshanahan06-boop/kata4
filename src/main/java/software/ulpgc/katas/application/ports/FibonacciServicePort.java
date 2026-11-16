package software.ulpgc.katas.application.ports;

import software.ulpgc.katas.application.domain.FibonacciSeries;

public interface FibonacciServicePort {
    FibonacciSeries fetchSeries(int count);
}