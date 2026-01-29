package org.example.lecturethree.threadconfinement;

import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class Incrementer3 implements Runnable{
    private ThreadLocal<Integer> myIntHolder = new ThreadLocal<Integer>() {
        protected Integer initialValue() {
            return 0;
        }
    };

    public void run() {
        for (int i = 0; i < 100000; i++) {
            myIntHolder.set(myIntHolder.get() + 1);
        }
        System.out.println("Myint: " + myIntHolder.get());
    }

    public static void main(String[] args) {
        Incrementer3 incrementerone = new Incrementer3();

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
