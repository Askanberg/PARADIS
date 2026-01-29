package org.example.lecturethree.visibility;

public class Visibility5 implements Runnable {

    private static int myInt = 0;

    public void run() {
        myInt = 21;
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new Visibility5());
        thread.start();
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("myInt = " + myInt);

    }
}
