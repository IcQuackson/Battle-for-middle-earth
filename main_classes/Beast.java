package main_classes;

import java.util.Random;
import java.util.function.Supplier;

public interface Beast {
    
    Supplier<Integer> attackPointsLambdaSupplier = () -> {
        Random random = new Random(); 
        int upperbound = 100;
        //generate random values from 0-90
        return random.nextInt(upperbound);
    };
}
