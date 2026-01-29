package org.example.lectureone.hellos;

public class HelloFromThread5 implements Runnable{
    public void run() {
        System.out.println("Sleeping...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Hello from Thread 5");
    }

    public static void main(String[] args) {
        HelloFromThread5 hello = new HelloFromThread5();
        Thread myThread = new Thread(hello);
        try {
            myThread.start();
            myThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main thread finished!");
    }
}
