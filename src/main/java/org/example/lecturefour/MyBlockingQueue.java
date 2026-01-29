package org.example.lecturefour;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class MyBlockingQueue<E> {
    private Object lock = new Object();
    private Queue<E> items = new LinkedList<E>();
    private int capacity;
    private Semaphore takeSem;
    private Semaphore putSem;

    public MyBlockingQueue(int capacity) {
        this.capacity = capacity;
        this.takeSem = new Semaphore(capacity);
        this.putSem = new Semaphore(capacity);
        try {
            putSem.acquire(capacity);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    E take() {
        E item = null;
        try {
            takeSem.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        synchronized (lock) {
            item = items.remove();
        }
        putSem.release();
        return item;
    }

    void put(E item) {
        try {
            putSem.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        synchronized (lock) {
            items.add(item);
        }
        takeSem.release();
    }

//    public static void main(String[] args) {
//        MyBlockingQueue barrier = new MyBlockingQueue(2);
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("Thread1: Before await()");
//                barrier.await();
//                System.out.println("Thread1: After await()");
//            }
//        }).start();
//        try {
//            Thread.sleep(1000); // Wait for threads to finish
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("Thread2: Before await()");
//                barrier.await();
//                System.out.println("Thread2: After await()");
//            }
//        }).start();
//    }
}
