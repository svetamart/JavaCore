package main.java.org.regular;

public class PrimeNumberCheck {
    public static boolean checkIfPrime(int number) {
    for (int i = 2; i <= number / 2; i++) {
        int remainder = number % i;
        if (remainder == 0) {
            return false;
        }
    }
    return true;
}
}
