// Andreas Skånberg, ansk4706

package org.example.assignments.two;

import java.util.List;
import java.util.ArrayList;

class Bank {
	// Instance variables.
	private final List<Account> accounts = new ArrayList<Account>();
	
	// Instance methods.

	 /*
	  * Changed the newAccount method to synchronized,
	    to ensure thread safety when multiple threads try to create new accounts at the same time using javas "intrinsic lock",
	    to prevent for example two accounts getting the same id if two threads try to create accounts at the same time.
	  */
	synchronized int newAccount(int balance) {
		int accountId = accounts.size();
		accounts.add(new Account(accountId, balance));
		return accountId;
	}

	/*
	 * I was not sure whether to make this method synchronized or not,
	   since the program in the assignment never uses newAccount after the initialization of the accounts which happens at the start,
	   leaving no risk of race conditions/visibility issues happening, I could have left it as it was.
	   But in general, since getAccount is quicker than newAccount
	   and it is possible that getAccount could be called while newAccount is still running,
	   I decided to make it synchronized as well to be safe.
	 */
	 synchronized Account getAccount(int accountId) {
		return accounts.get(accountId);
	}
}
