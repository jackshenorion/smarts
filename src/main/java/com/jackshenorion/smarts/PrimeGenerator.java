package com.jackshenorion.smarts;

public class PrimeGenerator {

    private int current = 2;

    public int next() {
        int i = current;
        while (true) {
            if (isPrime(++i)) {
                current = i;
                return current;
            }
        }
    }

    private boolean isPrime(int n) {
        if (n % 2 == 0) {
            return false;
        }

        for (int i = 3; i < Math.sqrt(n) + 1; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

}
