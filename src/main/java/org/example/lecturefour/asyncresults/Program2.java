package org.example.lecturefour.asyncresults;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Program2 {
    private static String getHello() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "Hello";
    }

    private static String concatHello(String str) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return str + "--Hello";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("Before any call...");

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> getHello());
        System.out.println("After first call...");

        CompletableFuture<String> future2 = future1.thenApply((x) -> concatHello(x));
        System.out.println("After second call...");

        CompletableFuture<String> future3 = future2.thenApply((x) -> concatHello(x));
        System.out.println("After third call...");

        CompletableFuture<Void> future4 = future3.thenAccept(System.out::println);
        System.out.println("After last call...");

        future4.get();
        System.out.println("Finished.");
    }
}
