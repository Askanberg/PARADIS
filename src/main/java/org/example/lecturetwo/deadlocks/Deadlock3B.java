package org.example.lecturetwo.deadlocks;

public class Deadlock3B implements Runnable{
    Deadlock3A lockerA;

    Deadlock3B(Deadlock3A lockerA){
        this.lockerA = lockerA;
    }

    synchronized void methodB1(){
        System.out.println("Taken lockerB in methodB1");
        Thread.yield();
        System.out.println("Calling lockerA.methodA2() from methodB1");
        lockerA.methodA2();
        System.out.println("Realeasing lockerB in methodB1");
    }

    synchronized void methodB2(){
        System.out.println("Taken lockerB in methodB2");
        System.out.println("Realeasing lockerB in methodB2");
    }

    public void run(){
        methodB1();
    }
}
