package org.example.lecturefour.asyncresults;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Program3 {
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

        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> getHello())
                .thenApply((x) -> concatHello(x))
                .thenApply((x) -> concatHello(x))
                .thenAccept(System.out::println);

        future.get();
    }
}
