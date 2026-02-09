// Andreas Skånberg, ansk4706

package org.example.assignments.two;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Transaction implements Runnable {
	private List<Operation> operations = new ArrayList<Operation>();
	private boolean closed = false;
	
	void add(Operation operation) {
		if (closed) return;
		operations.add(operation);
	}

	void close() {
		closed = true;
	}

	/*
	 * First I make a copy of the operations list to avoid holding the lock on the transaction while e.g running the operations.
	 * I sort the operations by account id before locking, to ensure that all threads/transactions
	   lock the accounts in the same order, preventing deadlocks.
	 * Locks are acquired for all operations in the transaction before executing any of them,
	   and is released if all operations have been successfully executed.
	 * PS. I thought about closing the flag in the beginning of the run method but since it's done in the program manually I did not.
	 */
	public void run() {
		List<Operation> copyOperations;

		synchronized (this) {
			if (!closed) return;
			copyOperations = new ArrayList<>(operations);
		}

		Collections.sort(copyOperations, Comparator.comparingInt(Operation::getAccountId));

		for (Operation op : copyOperations) {
			op.getAccount().lock.lock();
		}

		try {
			for (Operation op : copyOperations) {
				op.run();
			}
		} finally {
			for (Operation op : copyOperations) {
				op.getAccount().lock.unlock();
			}
		}
	}
}	
