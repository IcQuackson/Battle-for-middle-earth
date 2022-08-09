package main_classes;

import java.util.Random;
import java.util.function.Supplier;

public interface Hero {
    
    Supplier<Integer> attackPointsLambdaSupplier = () -> {
        Random random = new Random(); 
        int upperbound = 100;
        //generate random values from 0-100
        int firstDiceRoll = random.nextInt(upperbound); 
        int secondDiceRoll = random.nextInt(upperbound);

        if (firstDiceRoll >= secondDiceRoll) {
            return firstDiceRoll;
        }
        else {
            return secondDiceRoll;
        }
    };
}
