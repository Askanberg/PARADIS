package org.example;

import org.example.lectureone.hellos.HelloFromThread1;

public class Main {
    public static void main(String[] args) {
        HelloFromThread1 thread1 = new HelloFromThread1();
        thread1.start();
    }

}