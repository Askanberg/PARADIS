package org.example.lecturethree.instanceconfinement;

import net.jcip.annotations.NotThreadSafe;

@NotThreadSafe
public class Incrementer2 implements Runnable{
    private IntHolder myInt;

    public Incrementer2(IntHolder myInt) {
        this.myInt = myInt;
    }

    public synchronized void increase() {
        myInt.setValue(myInt.getValue() + 1);
    }

    public synchronized IntHolder getValue() {
        return myInt;
    }

    public void run() {
        for (int i = 0; i < 100000; i++) {
            increase();
            Thread.yield();
        }
        System.out.println("Incrementer1.myInt = " + myInt.getValue());
    }

    public static void main(String[] args) {
        Incrementer1 incrementerone = new Incrementer1();
        Incrementer2 incrementertwo = new Incrementer2(incrementerone.getValue());

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
    }
}
