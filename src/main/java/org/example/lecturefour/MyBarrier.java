package org.example.lecturefour;

public class MyBarrier {
    private final int parties;
    private int count = 0;
    private Object lock = new Object();

    public MyBarrier(int parties) {
        this.parties = parties;
    }

    void await() {
        synchronized (lock) {
            count++;
            if (count == parties) {
                lock.notifyAll();
                count = 0;
            }else {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    System.out.println("Thread interrupted: " + e.getMessage());
                }
            }
        }
    }

    public static void main(String[] args) {
        MyBarrier barrier = new MyBarrier(2);

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread1: Before await()");
                barrier.await();
                System.out.println("Thread1: After await()");
            }
        }).start();
        try {
            Thread.sleep(1000); // Wait for threads to finish
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread2: Before await()");
                barrier.await();
                System.out.println("Thread2: After await()");
            }
        }).start();
    }
}
