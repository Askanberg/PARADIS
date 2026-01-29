package org.example.lecturethree.threadconfinement;

import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class Incrementer2 implements Runnable{

    public void run() {
        int myInt = 0;
        for (int i = 0; i < 100000; i++) {
            myInt++;
        }
        System.out.println("Myint: " + myInt);
    }

    public static void main(String[] args) {
        Incrementer2 incrementerone = new Incrementer2();

        Thread thread1 = new Thread(incrementerone);
        Thread thread2 = new Thread(incrementerone);

        try {
            thread1.start();
            thread2.start();
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
