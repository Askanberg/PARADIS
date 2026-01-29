package org.example.lecturethree.instanceconfinement;

import net.jcip.annotations.ThreadSafe;

// Monitor Pattern variant with Instance Confinement
@ThreadSafe
public class Incrementer3 implements Runnable{
    private Counter1 counter = new Counter1();

    public void run() {
        for (int i = 0; i < 100000; i++) {
            counter.increment();
        }
    }

    public static void main(String[] args) {
        Incrementer3 incrementerthree = new Incrementer3();

        Thread thread1 = new Thread(incrementerthree);
        Thread thread2 = new Thread(incrementerthree);

        try {
            thread1.start();
            thread2.start();
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Final counter value: " + incrementerthree.counter.getCount());
    }
}
