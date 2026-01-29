package org.example.lecturetwo.incrementers;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.NotThreadSafe;

@NotThreadSafe
public class Incrementer7 implements Runnable {
    @GuardedBy("this")
    private int myInt = 0;

    private synchronized void increment() {
        myInt++;
    }

    public void run() {
        for (int i = 0; i < 100000; i++) {
            increment();
        }
    }

    public static void main(String[] args) {
        Incrementer7 incrementer = new Incrementer7();
        Incrementer7 incrementer2 = new Incrementer7();

        Thread thread1 = new Thread(incrementer);
        Thread thread2 = new Thread(incrementer2);

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
