package org.example.lecturethree.visibility;

public class Visibility1 implements Runnable {

    private static boolean ok = false;
    private static int myInt = 0;

    public void run() {
        while (true) {
            if (ok) {
                System.out.println("myInt = " + myInt);
                return;
            } else {
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new Visibility1());
        thread.start();

        myInt = 21;
        ok = true;
    }
}
