package org.example.lecturethree.threadconfinement;

import net.jcip.annotations.NotThreadSafe;

@NotThreadSafe
public class Incrementer1 implements Runnable{
    private int myInt = 0;

    public void run() {
        for (int i = 0; i < 100000; i++) {
            myInt++;
        }
    }

    public static void main(String[] args) {
        Incrementer1 incrementerone = new Incrementer1();
        Incrementer1 incrementertwo = new Incrementer1();

        Thread thread1 = new Thread(incrementerone);
        Thread thread2 = new Thread(incrementertwo);

        try {
            thread1.start();
            thread2.start();
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Myint1: " + incrementerone.myInt);
        System.out.println("Myint2: " + incrementertwo.myInt);

    }
}
