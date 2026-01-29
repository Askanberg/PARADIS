package org.example.lecturetwo.deadlocks;

import com.sun.security.jgss.GSSUtil;

public class Deadlocker2 implements Runnable {
    Account from;
    Account to;
    int amount;

    Deadlocker2(Account from, Account to, int amount) {
        this.from = from ;
        this.to = to;
        this.amount = amount;
    }

    public void run() {
        transferMoney();
    }

    void transferMoney() {
        synchronized (from) {
            System.out.println("Taken (From): " + from.id);
            Thread.yield();
            synchronized (to) {
                System.out.println("Taken (to): " + to.id);
                from.debit(amount);
                to.credit(amount);
            }
            System.out.println("Released (to): " + to.id);
        }
        System.out.println("Released (from): " + from.id);
    }



    public static void main(String[] args) {
        Account account1 = new Account(1, 1000);
        Account account2 = new Account(2, 2000);
        Deadlocker2 deadlocker1 = new Deadlocker2(account1, account2, 100);
        Deadlocker2 deadlocker2 = new Deadlocker2(account2, account1, 200);
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
