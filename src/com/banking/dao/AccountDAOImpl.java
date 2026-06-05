package com.banking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.banking.model.Account;
import com.banking.util.DBUtil;

public class AccountDAOImpl implements AccountDAO {

	@Override
	public void createAccount(Account acc) {
		try {
			Connection con = DBUtil.getConnection();

			String sql = "INSERT INTO accounts VALUES(?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, acc.getAccNo());
			ps.setString(2, acc.getName());
			ps.setDouble(3, acc.getBalance());

			int rows = ps.executeUpdate();

			System.out.println(rows + " account created");

			ps.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public Account getAccount(int accNo) {
		Account acc = null;
		try {
			Connection con = DBUtil.getConnection();

			String sql = "SELECT * FROM accounts WHERE acc_no=?";

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, accNo);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				acc = new Account(rs.getInt("acc_no"), rs.getString("name"), rs.getDouble("balance"));
			}
			rs.close();
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return acc;
	}

	@Override
	public void updateAccount(int accNo, double balance) {
		try {
			Connection con = DBUtil.getConnection();

			String sql = "UPDATE accounts SET balance=? WHERE acc_no=?";
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(2, accNo);
			ps.setDouble(1, balance);

			ps.executeUpdate();

			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Account> getAllAccounts() {
		List<Account> list = new ArrayList<>();
		try {
			Connection con = DBUtil.getConnection();

			String sql = "SELECT * FROM accounts";

			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Account acc = new Account(rs.getInt("acc_no"), rs.getString("name"), rs.getDouble("balance"));
				list.add(acc);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
