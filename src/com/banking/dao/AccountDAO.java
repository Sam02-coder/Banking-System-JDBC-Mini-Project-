package com.banking.dao;

import java.util.List;

import com.banking.model.Account;

public interface AccountDAO {
	void createAccount(Account acc);

	Account getAccount(int accNo);

	void updateBalance(int accNo, double balance);

	List<Account> getAllAccounts();

}
