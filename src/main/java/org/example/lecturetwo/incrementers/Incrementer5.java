package org.example.lecturetwo.incrementers;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class Incrementer5 implements Runnable{
    @GuardedBy("lock")
    private int myInt = 0;
    private Object lock = new Object();

    public void run() {
        for (int i = 0; i < 100000; i++) {
            synchronized (lock) {
                myInt++;
            }
        }
    }

    public static void main(String[] args) {
        Incrementer5 incrementer = new Incrementer5();

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
