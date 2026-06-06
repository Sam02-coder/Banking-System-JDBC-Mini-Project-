package com.banking.service;

import java.sql.Connection;

import com.banking.dao.AccountDAO;
import com.banking.dao.AccountDAOImpl;
import com.banking.model.Account;
import com.banking.util.DBUtil;

public class BankServiceImpl implements BankService {
	AccountDAO dao = new AccountDAOImpl();

	@Override
	public void deposit(int accNo, double amount) {

		Account acc = dao.getAccount(accNo);

		if (acc != null) {

			double newBalance = acc.getBalance() + amount;

			dao.updateBalance(accNo, newBalance);

			System.out.println("Amount Deposited");
		}
	}

	@Override
	public void withdraw(int accNo, double amount) {

		Account acc = dao.getAccount(accNo);

		if (acc != null) {

			if (acc.getBalance() >= amount) {

				double newBalance = acc.getBalance() - amount;

				dao.updateBalance(accNo, newBalance);

				System.out.println("Amount Withdrawn");

			} else {

				System.out.println("Insufficient Balance");
			}
		}
	}

	/*
	 * TRANSACTION MANAGEMENT
	 */
	@Override
	public void transfer(int fromAcc, int toAcc, double amount) {

		try {

			Connection con = DBUtil.getConnection();

			/*
			 * Disable auto commit
			 */
			con.setAutoCommit(false);

			Account sender = dao.getAccount(fromAcc);

			Account receiver = dao.getAccount(toAcc);

			if (sender.getBalance() >= amount) {

				double senderBalance = sender.getBalance() - amount;

				double receiverBalance = receiver.getBalance() + amount;

				dao.updateBalance(fromAcc, senderBalance);

				dao.updateBalance(toAcc, receiverBalance);

				/*
				 * Save permanently
				 */
				con.commit();

				System.out.println("Transfer Successful");

			} else {

				System.out.println("Insufficient Balance");

				/*
				 * Undo changes
				 */
				con.rollback();
			}

			con.close();

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Override
	public void checkBalance(int accNo) {

		Account acc = dao.getAccount(accNo);

		if (acc != null) {

			System.out.println("Balance: " + acc.getBalance());
		}
	}

	@Override
	public void viewAccounts() {

		dao.getAllAccounts().forEach(System.out::println);

	}
}
