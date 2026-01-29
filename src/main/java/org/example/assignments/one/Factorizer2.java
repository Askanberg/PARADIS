//Andreas Skånberg, ansk4706

package org.example.assignments.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Factorizer2 implements Runnable{

    private final BigInteger startValue;
    private final BigInteger max;
    private final int step;
    private final BigInteger product;

    private static BigInteger factor1 = null;
    private static BigInteger factor2 = null;
    public static boolean found = false;

    private static final Object lock = new Object();


    public Factorizer2(BigInteger startValue, int step, BigInteger product) {
        this.startValue = startValue;
        this.step = step;
        this.product = product;
        this.max = product.sqrt();

    }

    @Override
    public void run() {
        BigInteger number = startValue;

        while (number.compareTo(max) <= 0) {
            if (found) return;

            if (product.remainder(number).equals(BigInteger.ZERO)) {
                synchronized (lock) {
                    if (!found) {
                        factor1 = number;
                        factor2 = product.divide(number);
                        found = true;
                    }

                }
                return;
            }

            number = number.add(BigInteger.valueOf(step));
        }
    }

    public static void main(String[] args) {
        try {
            found = false;
            factor1 = null;
            factor2 = null;


            InputStreamReader streamReader = new InputStreamReader(System.in);
            BufferedReader consoleReader = new BufferedReader(streamReader);

            System.out.println("Enter number of threads: ");
            int numberOfThreads = Integer.parseInt(consoleReader.readLine());
            System.out.println("Enter product: ");
            BigInteger product = new BigInteger(consoleReader.readLine());

            long start = System.nanoTime();

            Thread[] threads = new Thread[numberOfThreads];
            Factorizer2[] primeFinders = new Factorizer2[numberOfThreads];

            for (int i = 0; i < numberOfThreads; i++) {
                BigInteger startValue = BigInteger.valueOf(2 + i);
                primeFinders[i] = new Factorizer2(startValue, numberOfThreads, product);
                threads[i] = new Thread(primeFinders[i]);
            }

            for (int i = 0; i < numberOfThreads; i++) {
                threads[i].start();
            }

            for (int i = 0; i < numberOfThreads; i++) {
                threads[i].join();
            }

            long stop = System.nanoTime();

            if (factor1 == null) {
                System.out.println("No factorization possible");
            } else {
                System.out.println("Factor 1: " + factor1);
                System.out.println("Factor 2: " + factor2);
            }

            System.out.println("Range searched: [2, " + product.sqrt() + "]");
            System.out.println("Available processors: " + Runtime.getRuntime().availableProcessors());
            System.out.println("Number of threads used: " + numberOfThreads);
            System.out.println("Time taken (seconds): " + (stop - start) / 1_000_000_000.0);


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
