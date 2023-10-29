package org.example.algorithm;

public class NumberDivisibility {

    public NumberDivisibility(){
    }

    public boolean isDivisibleBy2(int number){
        return number % 2 == 0;
    }

    public boolean isDivisibleBy3(int number){
        return number % 3 == 0;
    }

    public boolean isDivisibleBy5(int number){
        return number % 5 == 0;
    }

    public boolean isDivisibleBy(int number, int divider){
        return switch (divider) {
            case 2 -> isDivisibleBy2(number);
            case 3 -> isDivisibleBy3(number);
            case 5 -> isDivisibleBy5(number);
            default ->
                    throw new IllegalArgumentException("This algorithm support only checking if function is divisible by: 2, 3, or 5.");
        };
    }

}

