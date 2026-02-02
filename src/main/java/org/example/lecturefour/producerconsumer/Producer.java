package org.example.lecturefour.producerconsumer;

import java.util.Random;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    private BlockingQueue<Task> queue;
    private Random random = new Random();

    public Producer(BlockingQueue<Task> queue) {
        this.queue = queue;
    }

    public void run() {
        while (true) {
            try {
                Task task = produce();
                queue.put(task);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Task produce() {
        // Simulate producing a task
        try {
            Thread.sleep(random.nextInt(1000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return new Task("Hello!");
    }
}
