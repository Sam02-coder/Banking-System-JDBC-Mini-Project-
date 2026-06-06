package com.banking.service;

public interface BankService {
	void deposit(int accNo, double amount);

	void withdraw(int accNo, double amount);

	void transfer(int fromAcc, int toAcc, double amount);

	void checkBalance(int accNo);

	void viewAccounts();
}
