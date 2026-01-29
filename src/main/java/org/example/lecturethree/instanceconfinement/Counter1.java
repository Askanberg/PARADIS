package org.example.lecturethree.instanceconfinement;

import net.jcip.annotations.ThreadSafe;

// Monitor Pattern variant
@ThreadSafe
public class Counter1 {
    private int count = 0;
    private Object lock = new Object();

    public void increment() {
        synchronized (lock) {
            count++;
        }

    }

    public int getCount() {
        synchronized (lock) {
            return count;
        }
    }
}
