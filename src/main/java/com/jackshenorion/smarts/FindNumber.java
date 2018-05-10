package com.jackshenorion.smarts;

public class FindNumber {

    public static void main(String[] args) {
        long i = 1, j = 1, k = 1;
        final long max = Long.MAX_VALUE;
        for (i = 1; i < max; i++) {
            if (i % 100 == 0) {
                System.out.println("Check point: " + i);
            }
            long min = i / 8;
            for (j = min; j <= i / 3; j++) {
                for (k = min; k <= j; k++) {
                    long left = i * (i + j) * (i + k) + j * (j + i) * (j + k) + k * (k + i) * (k + j);
                    long right = 4 * (j + k) * (i + j) * (i + k);
                    if (left == right) {
                        System.out.printf("%d, %d, %d, ", i, j, k);
                        double di = i;
                        double dj = j;
                        double dk = k;
                        System.out.print(di / (dj + dk) + dj / (di + dk) + dk / (di + dj));
                        return;
                    }
                }
            }
        }

        System.out.println("No result");
    }

}
