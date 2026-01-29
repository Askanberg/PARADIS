package org.example.lecturetwo.incrementers;

import net.jcip.annotations.GuardedBy;

public class Incrementer9 implements Runnable{
    @GuardedBy("lock")
    private int myInt1 = 0;
    @GuardedBy("lock")
    private int myInt2 = 0;

    private Object lock = new Object();



    public void run() {
        for (int i = 0; i < 100000; i++) {
            synchronized (lock){
                if ((myInt1 + myInt2) % 2 == 0) {
                    myInt1++;
                } else {
                    myInt2++;
                }
            }
        }
    }

    public static void main(String[] args) {
        Incrementer9 incrementer = new Incrementer9();

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

        System.out.println("Final value of myInt: " + incrementer.myInt1);
        System.out.println("Final value of myInt: " + incrementer.myInt2);
    }
}
