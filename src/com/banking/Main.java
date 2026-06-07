package com.banking;

import java.util.Scanner;

import com.banking.dao.AccountDAO;
import com.banking.dao.AccountDAOImpl;
import com.banking.model.Account;
import com.banking.service.BankService;
import com.banking.service.BankServiceImpl;

public class Main {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		AccountDAO dao = new AccountDAOImpl();

		BankService service = new BankServiceImpl();

		while (true) {

			System.out.println("\n===== BANK SYSTEM =====");

			System.out.println("1. Create Account");

			System.out.println("2. Deposit");

			System.out.println("3. Withdraw");

			System.out.println("4. Transfer");

			System.out.println("5. Check Balance");

			System.out.println("6. View Accounts");

			System.out.println("7. Exit");

			System.out.print("Enter Choice: ");

			int choice = sc.nextInt();

			switch (choice) {

			case 1:

				System.out.print("Enter Account No: ");

				int accNo = sc.nextInt();

				sc.nextLine();

				System.out.print("Enter Name: ");

				String name = sc.nextLine();

				System.out.print("Enter Balance: ");

				double balance = sc.nextDouble();

				Account acc = new Account(accNo, name, balance);

				dao.createAccount(acc);

				break;

			case 2:

				System.out.print("Enter Account No: ");

				int depositAcc = sc.nextInt();

				System.out.print("Enter Amount: ");

				double depositAmount = sc.nextDouble();

				service.deposit(depositAcc, depositAmount);

				break;

			case 3:

				System.out.print("Enter Account No: ");

				int withdrawAcc = sc.nextInt();

				System.out.print("Enter Amount: ");

				double withdrawAmount = sc.nextDouble();

				service.withdraw(withdrawAcc, withdrawAmount);

				break;

			case 4:

				System.out.print("From Account: ");

				int fromAcc = sc.nextInt();

				System.out.print("To Account: ");

				int toAcc = sc.nextInt();

				System.out.print("Enter Amount: ");

				double transferAmount = sc.nextDouble();

				service.transfer(fromAcc, toAcc, transferAmount);

				break;

			case 5:

				System.out.print("Enter Account No: ");

				int balanceAcc = sc.nextInt();

				service.checkBalance(balanceAcc);

				break;

			case 6:

				service.viewAccounts();

				break;

			case 7:

				System.out.println("Application Closed");

				System.exit(0);
			}
		}

	}

}
