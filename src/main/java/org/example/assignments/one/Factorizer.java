//Andreas Skånberg, ansk4706

package org.example.assignments.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Factorizer implements Runnable{

    static class Result {
        BigInteger factor1 = null;
        BigInteger factor2 = null;
        boolean found = false;
        final Object lock = new Object();
    }

    private final BigInteger startValue;
    private final BigInteger max;
    private final int step;
    private final BigInteger product;
    private final Result result;


    public Factorizer(BigInteger startValue, int step, BigInteger product, Result result) {
        this.startValue = startValue;
        this.step = step;
        this.product = product;
        this.max = product.sqrt();
        this.result = result;

    }

    @Override
    public void run() {
        BigInteger number = startValue;

        while (number.compareTo(max) <= 0) {
            if (result.found) return;

            if (product.remainder(number).equals(BigInteger.ZERO)) {
                synchronized (result.lock) {
                    if (!result.found) {
                        result.factor1 = number;
                        result.factor2 = product.divide(number);
                        result.found = true;
                    }

                }
                return;
            }

            number = number.add(BigInteger.valueOf(step));
        }
    }

    public static void main(String[] args) {
        try {

            InputStreamReader streamReader = new InputStreamReader(System.in);
            BufferedReader consoleReader = new BufferedReader(streamReader);

            System.out.println("Enter number of threads: ");
            int numberOfThreads = Integer.parseInt(consoleReader.readLine());
            if (numberOfThreads <= 0) {
                throw new RuntimeException("Number of threads cannot be smaller than 0");
            }

            System.out.println("Enter product: "); //Example prime number: 51564514170775987
            BigInteger product = new BigInteger(consoleReader.readLine());
            if (product.compareTo(BigInteger.TWO) < 0) {
                throw new RuntimeException("Product must be at least 2");
            }

            long start = System.nanoTime();

            Thread[] threads = new Thread[numberOfThreads];
            Factorizer[] factorFinders = new Factorizer[numberOfThreads];

            Result result = new Result();

            for (int i = 0; i < numberOfThreads; i++) {
                BigInteger startValue = BigInteger.valueOf(2 + i);
                factorFinders[i] = new Factorizer(startValue, numberOfThreads, product, result);
                threads[i] = new Thread(factorFinders[i]);
            }

            for (int i = 0; i < numberOfThreads; i++) {
                threads[i].start();
            }

            for (int i = 0; i < numberOfThreads; i++) {
                threads[i].join();
            }

            long stop = System.nanoTime();

            if (result.factor1 == null) {
                System.out.println("No factorization possible");
            } else {
                System.out.println("Factor 1: " + result.factor1);
                System.out.println("Factor 2: " + result.factor2);
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

