package main.java.org.regular;

public class Decorator {

    public static String decoratePrime(int a){
        return String.format("%d is a prime number.", a);
    }

    public static String decorateNotPrime(int a){
        return String.format("%d is not a prime number.", a);
    }

}
