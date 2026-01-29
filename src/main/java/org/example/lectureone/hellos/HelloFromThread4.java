package org.example.lectureone.hellos;

public class HelloFromThread4 implements Runnable {
    public void run() {
        System.out.println("Sleeping...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Hello from Thread 4");
    }

    public static void main(String[] args) {
        HelloFromThread4 hello = new HelloFromThread4();
        Thread myThread = new Thread(hello);
        myThread.start();
        System.out.println("Main thread finished!");
    }
}

