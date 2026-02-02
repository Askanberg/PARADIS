package org.example.lecturefour.producerconsumer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    private BlockingQueue<Task> queue;
    private Random random = new Random();

    public Consumer(BlockingQueue<Task> queue) {
        this.queue = queue;
    }

    public void run() {
        while (true) {
            try {
                Task task = queue.take();
                consume(task);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void consume(Task task) {
        // Simulate producing a task
        try {
            Thread.sleep(random.nextInt(1000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(task.getData());
    }
}
