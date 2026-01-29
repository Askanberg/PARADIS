package org.example.lecturefour;

public class MyLatch {
    private int count;
    private Object lock = new Object();

    public MyLatch(int count) {
        this.count = count;
    }

    void await() {
        synchronized (lock) {
            if (count > 0) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    System.out.println("Thread interrupted: " + e.getMessage());
                }
            }
        }
    }

    void countDown() {
        synchronized (lock) {
            if (count > 0) {
                count--;
                if (count == 0) {
                    lock.notifyAll();
                }
            }
        }
    }

    public static void main(String[] args) {
        MyLatch latch = new MyLatch(1);

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread1: Before await()");
                latch.await();
                System.out.println("Thread1: After await()");
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread2: Before await()");
                latch.await();
                System.out.println("Thread2: After await()");
            }
        }).start();
        try {
            Thread.sleep(1000); // Wait for threads to finish
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Before countDown()");
        latch.countDown();
        System.out.println("After countDown()");

    }
}
