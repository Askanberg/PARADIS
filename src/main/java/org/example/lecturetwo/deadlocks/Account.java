package org.example.lecturetwo.deadlocks;

import java.util.concurrent.locks.ReentrantLock;

public class Account {
    final int id;
    int balance;
    ReentrantLock lock;

    public Account(int id, int balance) {
        this.id = id;
        this.balance = balance;
        lock = new ReentrantLock();
    }

    void debit(int amount) {
        balance -= amount;
    }
    void credit(int amount) {
        balance += amount;
    }
}
