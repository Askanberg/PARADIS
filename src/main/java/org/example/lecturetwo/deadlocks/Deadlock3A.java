package org.example.lecturetwo.deadlocks;

public class Deadlock3A implements Runnable{

    Deadlock3B lockerB = new Deadlock3B(this);

    synchronized void methodA1(){
        System.out.println("Taken lockerA in methodA1");
        Thread.yield();
        System.out.println("Calling lockerB.methodB2() from methodA1");
        lockerB.methodB2();
        System.out.println("Realeasing lockerA in methodA1");
    }

    synchronized void methodA2(){
        System.out.println("Taken lockerA in methodA2");
        System.out.println("Realeasing lockerA in methodA2");
    }

    public void run(){
        methodA1();
    }

    public static void main(String[] args){
        Deadlock3A lockerA = new Deadlock3A();


        Thread threadA = new Thread(lockerA);
        Thread threadB = new Thread(lockerA.lockerB);

        try{
            threadA.start();
            threadB.start();
            threadA.join();
            threadB.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("Done!");
    }
}
