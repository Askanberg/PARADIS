package org.example.lecturethree.instanceconfinement;

import net.jcip.annotations.NotThreadSafe;

@NotThreadSafe
public class Incrementer1 implements Runnable{
    private IntHolder myInt = new IntHolder(0);

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
}
