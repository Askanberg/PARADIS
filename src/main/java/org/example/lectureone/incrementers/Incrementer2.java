package org.example.lectureone.incrementers;

import net.jcip.annotations.NotThreadSafe;

@NotThreadSafe
public class Incrementer2  implements Runnable{
    private int myInt = 0;

    public void run() {
        for (int i = 0; i < 100000; i++) {
            myInt++;
        }
    }

    public static void main(String[] args) {
        Incrementer2 incrementer = new Incrementer2();

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
