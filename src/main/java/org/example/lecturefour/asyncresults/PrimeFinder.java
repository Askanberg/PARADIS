package org.example.lecturefour.asyncresults;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.*;

public class PrimeFinder implements Callable<Integer> {

    private final static long MAX = 2000000;
    private final static long MIN = 1;

    private long min;
    private long max;
    private int step;
    private int counter;

    PrimeFinder(long min, long max, int step) {
        this.min = min;
        this.max = max;
        this.step = step;
        counter = 0;
    }

    static boolean isPrime(long number) {
        boolean result = true;
        for (long d = 2; d < Math.sqrt(number); d++) {
            if (number % d == 0) {
                result = false;
            }
        }
        return result;
    }

    public Integer call() {
        for (long i = min; i <= max; i += step) {
            if (isPrime(i)) {
                counter++;
            }
        }
        return counter;
    }

    public static void main(String[] args) {
        try {
            InputStreamReader streamReader = new InputStreamReader(System.in);
            BufferedReader consoleReader = new BufferedReader(streamReader);
            System.out.println("Enter number of threads: ");
            int numberOfThreads = Integer.parseInt(consoleReader.readLine());

            ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
            PrimeFinder[] finders = new PrimeFinder[numberOfThreads];
            for (int i = 0; i < numberOfThreads; i++) {
                finders[i] = new PrimeFinder(MIN + i, MAX, numberOfThreads);
            }

            long start = System.nanoTime();

            Future[] futures = new Future[numberOfThreads];
            for (int i = 0; i < numberOfThreads; i++) {
                futures[i] = executor.submit(finders[i]);
            }

            int totalCounter = 0;
            for (int i = 0; i < numberOfThreads; i++) {
                totalCounter += (Integer) futures[i].get();
            }
            long stop = System.nanoTime();

            executor.shutdown();

            int numberOfCores = Runtime.getRuntime().availableProcessors();
            System.out.println("Range searched: [" + MIN + ", " + MAX + "]" );
            System.out.println("Number of primes found: " + totalCounter );
            System.out.println("Available processors: " + numberOfCores );
            System.out.println("Number of threads used: " + numberOfThreads );
            System.out.println("Time taken (seconds): " + (stop - start) / 1_000_000_000.0 );

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
