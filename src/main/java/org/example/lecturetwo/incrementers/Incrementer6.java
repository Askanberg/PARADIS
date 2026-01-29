package org.example.lecturetwo.incrementers;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@ThreadSafe
public class Incrementer6 implements Runnable{
    @GuardedBy("lock")
    private int myInt = 0;
    private Lock lock = new ReentrantLock();

    public void run() {
        for (int i = 0; i < 100000; i++) {
            lock.lock();
            try {
                myInt++;
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        Incrementer6 incrementer = new Incrementer6();

        Thread thread1 = new Thread(incrementer);
        Thread thread2 = new Thread(incrementer);

        try {
            thread1.start();
            thread2.start();
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final value of myInt: " + incrementer.myInt);
    }
}
