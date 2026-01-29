package org.example.lectureone.hellos;

public class HelloFromThread2 implements Runnable{
    @Override
    public void run() {
        System.out.println("Hello from Thread 2");
    }

    public static void main(String[] args) {
        HelloFromThread2 hello = new HelloFromThread2();
        Thread thread = new Thread(hello);
        thread.start();
    }
}
