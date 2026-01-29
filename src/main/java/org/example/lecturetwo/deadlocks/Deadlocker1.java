package org.example.lecturetwo.deadlocks;

import com.sun.security.jgss.GSSUtil;

public class Deadlocker1 implements Runnable {
    Account account1;
    Account account2;
    boolean inOneOrder;

    Deadlocker1(Account account1, Account account2, boolean inOneOrder) {
        this.account1 = account1;
        this.account2 = account2;
        this.inOneOrder = inOneOrder;
    }

    void lockInOneOrder() {
        synchronized (account1) {
            System.out.println("Taken (LockInOneOrder): " + account1.id);
            Thread.yield();
            synchronized (account2) {
                System.out.println("Taken (LockInOneOrder): " + account2.id);
            }
            System.out.println("Released (LockInOneOrder): " + account2.id);
        }
        System.out.println("Released (LockInOneOrder): " + account1.id);
    }

    void lockInAnotherOrder() {
        synchronized (account2) {
            System.out.println("Taken(LockInAnotherOrder): " + account2.id);
            Thread.yield();
            synchronized (account1) {
                System.out.println("Taken(LockInAnotherOrder): " + account1.id);
            }
            System.out.println("Released (LockInAnotherOrder): " + account1.id);
        }
        System.out.println("Released (LockInAnotherOrder): " + account2.id);
    }

    public void run() {
        if (inOneOrder) {
            lockInOneOrder();
        } else {
            lockInAnotherOrder();
        }
    }

    public static void main(String[] args) {
        Account account1 = new Account(1, 1000);
        Account account2 = new Account(2, 2000);
        Deadlocker1 deadlocker1 = new Deadlocker1(account1, account2, true);
        Deadlocker1 deadlocker2 = new Deadlocker1(account1, account2, false);
        Thread thread1 = new Thread(deadlocker1);
        Thread thread2 = new Thread(deadlocker2);
        try {
            thread1.start();
            thread2.start();
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Done!");
    }
}
