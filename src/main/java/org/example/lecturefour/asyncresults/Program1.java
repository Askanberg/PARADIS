package org.example.lecturefour.asyncresults;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Program1 implements Callable<String> {

    public String call(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "Hello from Callable!";
    }

    public static void main(String[] args) {
        Future<String> result = new AsyncResult<String>(new Program1());
        while (!result.isDone()) {
            System.out.println("Do some work in between.");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            System.out.println("Result: " + result.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
