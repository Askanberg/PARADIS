package org.example.lecturetwo.deadlocks;

import net.jcip.annotations.GuardedBy;

public class NonDeadlocker1 implements Runnable {
    @GuardedBy("from")
    Account from;
    Account to;
    int amount;

    NonDeadlocker1(Account from, Account to, int amount) {
        this.from = from ;
        this.to = to;
        this.amount = amount;
    }

    public void run() {
        transferMoney();
    }

    void transferMoney() {
        if(from.id < to.id){
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
            System.out.println("Taken (From): " + from.id);
        } else {
            synchronized (to) {
                System.out.println("Taken (to): " + to.id);
                Thread.yield();
                synchronized (from) {
                    System.out.println("Taken (From): " + from.id);
                    from.debit(amount);
                    to.credit(amount);
                }
                System.out.println("Released (From): " + from.id);
            }
            System.out.println("Released (to): " + to.id);
        }
    }



    public static void main(String[] args) {
        Account account1 = new Account(1, 1000);
        Account account2 = new Account(2, 2000);
        NonDeadlocker1 deadlocker1 = new NonDeadlocker1(account1, account2, 100);
        NonDeadlocker1 deadlocker2 = new NonDeadlocker1(account2, account1, 200);
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
