package org.example;

class Prime {

    public static int getFirst() {
        return 2;
    }

    public static int getNext(int num) {

        while (true) {
            num++;

            if (isPrime(num)) return num;
        }
    }

    private static boolean isPrime(int num) {

        for (int i = 2; i * i < num; i++) {
            if (num % i == 0) return false;
        }

        return true;
    }
}
