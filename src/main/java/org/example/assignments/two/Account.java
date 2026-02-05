// Andreas Skånberg, ansk4706

package org.example.assignments.two;

import java.util.concurrent.locks.ReentrantLock;

class Account {
	// Instance variables.
	private final int ID;
	private int balance;
	/*
	 * Added this lock to be able to use it in the transaction class when locking multiple accounts at the same time.
	 * I considered making the get/set methods synchronized and using the intrinsic lock of the account object,
	   but since I also needed to lock multiple accounts at the same time in the transaction class,
	   I decided to use a ReentrantLock instead.
	 */
	ReentrantLock lock = new ReentrantLock();
	
	// Constructor.
	Account(int id, int balance) {
		ID = id;
		this.balance = balance;
	}
	
	// Instance methods.

	// Since the ID is final and immutable, no locks are needed for the getId method.
	int getId() {
		return ID;
	}


	int getBalance() {
		lock.lock();
		try {
			return balance;
		} finally {
			lock.unlock();
		}
	}

	void setBalance(int amount) {
		lock.lock();
		try {
			this.balance += amount;
		} finally {
			lock.unlock();
		}
	}
}
