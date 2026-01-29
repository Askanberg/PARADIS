package org.example.lecturetwo.incrementers;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.NotThreadSafe;

@NotThreadSafe
public class Incrementer8 implements Runnable{
    @GuardedBy("lock1")
    private int myInt1 = 0;
    @GuardedBy("lock2")
    private int myInt2 = 0;

    private Object lock1 = new Object();
    private Object lock2 = new Object();



    public void run() {
        for (int i = 0; i < 100000; i++) {
            if (myInt1 + myInt2 % 2 == 0) {
                synchronized (lock1){
                    myInt1++;
                }
            } else {
                synchronized (lock2) {
                    myInt2++;
                }
            }
        }
    }

    public static void main(String[] args) {
        Incrementer8 incrementer = new Incrementer8();

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
