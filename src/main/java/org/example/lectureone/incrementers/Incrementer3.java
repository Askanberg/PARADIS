package org.example.lectureone.incrementers;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class Incrementer3 implements Runnable {
    @GuardedBy("this")
    private int myInt = 0;


    public void run() {
        for (int i = 0; i < 100000; i++) {
            synchronized (this) {
                myInt++;
            }
        }
    }

    public static void main(String[] args) {
        Incrementer3 incrementer = new Incrementer3();

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
