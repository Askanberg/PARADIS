package org.example.lecturethree.visibility;

public class Visibility4 implements Runnable {

    private static int myInt = 0;

    public void run() {
        myInt = 21;
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new Visibility4());
        thread.start();
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("myInt = " + myInt);

    }
}
