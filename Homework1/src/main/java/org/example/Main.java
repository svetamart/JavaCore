package main.java.org.example;

import main.java.org.regular.Decorator;
import main.java.org.regular.PrimeNumberCheck;

public class Main {
    public static void main(String[] args) {

        int number1 = 7;
        boolean result1 = PrimeNumberCheck.checkIfPrime(number1);
        if(result1) {
            System.out.println(Decorator.decoratePrime(number1));
        }
        else {
            System.out.println(Decorator.decorateNotPrime(number1));
        }

        int number2 = 4;
        boolean result2 = PrimeNumberCheck.checkIfPrime(number2);
        if(result2) {
            System.out.println(Decorator.decoratePrime(number2));
        }
        else {
            System.out.println(Decorator.decorateNotPrime(number2));
        }

    }
}