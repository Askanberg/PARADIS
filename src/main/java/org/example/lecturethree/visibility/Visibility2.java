package org.example.lecturethree.visibility;

import net.jcip.annotations.GuardedBy;

public class Visibility2 implements Runnable {
    @GuardedBy("lock")
    private static boolean ok = false;
    @GuardedBy("lock")
    private static int myInt = 0;
    private static Object lock = new Object();

    public void run() {
        while (true) {
            synchronized (lock) {
                if (ok) {
                    System.out.println("myInt = " + myInt);
                    return;
                } else {
                    Thread.yield();
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new Visibility2());
        thread.start();

        synchronized (lock) {
            myInt = 21;
            ok = true;
        }
    }
}
