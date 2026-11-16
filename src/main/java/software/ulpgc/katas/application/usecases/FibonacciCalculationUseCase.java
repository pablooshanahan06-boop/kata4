package software.ulpgc.katas.application.usecases;

import software.ulpgc.katas.application.domain.FibonacciSeries;
import java.util.ArrayList;
import java.util.List;

public class FibonacciCalculationUseCase {
    public FibonacciSeries calculate(int count) {
        List<Long> values = new ArrayList<>();
        long a = 0, b = 1;
        for (int i = 0; i < count; i++) {
            values.add(a);
            long next = a + b;
            a = b;
            b = next;
        }
        return new FibonacciSeries(count, values);
    }
}