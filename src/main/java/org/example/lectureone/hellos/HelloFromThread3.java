package org.example.lectureone.hellos;

public class HelloFromThread3 implements Runnable {
    public void run() {
        System.out.println("Hello from Thread 3");
        Thread.yield();
        System.out.println("Hello again from Thread 3");
    }

    public static void main (String[] args) {
        HelloFromThread3 hello = new HelloFromThread3();
        Thread myThread = new Thread(hello);
        myThread.start();
        System.out.println("Main thread finished!"  );
    }

}
