// Andreas Skånberg, ansk4706

package org.example.assignments.two;

/*
 * Thread safety is handled by the Account class using ReentrantLock.
 * Operation itself does not synchronize, but calling account.getBalance() and account.setBalance()
   is safe because these methods lock the account internally.
 * Multi-account operations is handled in Transaction by explicitly locking all involved accounts.
 * */
class Operation implements Runnable {
	private final int ACCOUNT_ID;
	private final int AMOUNT;
	private final Account account;
	
	Operation(Bank bank, int accountId, int amount) {
		ACCOUNT_ID = accountId;
		AMOUNT = amount;
		account = bank.getAccount(ACCOUNT_ID);
	}
	
	int getAccountId() {
		return ACCOUNT_ID;
	}

	//Added this method to be able to sort the operations by account id in the transaction class, to avoid deadlocks.
	Account getAccount() {
		return account;
	}

	/*
	 * Before the change here the method made the operation in two steps (getBalance + setBalance).
	 * Even when I had made each method locked in the Account class, the sequence of two method calls was not atomic.
	 * If I understand correctly this change makes the operation atomic by doing it in one step and
	   delegating the whole update to the accounts setBalance() instead of multiple operations.
	 * */
	public void run() {
		account.setBalance(AMOUNT);
	}
}	
