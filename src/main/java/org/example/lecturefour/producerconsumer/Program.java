package org.example.lecturefour.producerconsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Program {
    private static BlockingQueue<Task> queue = new ArrayBlockingQueue<>(10);

     public static void main(String[] args) {
        (new Thread(new Producer(queue))).start();
        (new Thread(new Producer(queue))).start();
        (new Thread(new Producer(queue))).start();
        (new Thread(new Producer(queue))).start();
        (new Thread(new Consumer(queue))).start();
        (new Thread(new Consumer(queue))).start();
        (new Thread(new Consumer(queue))).start();
        (new Thread(new Consumer(queue))).start();
    }
}
