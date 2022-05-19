package com.revature.banking.daos;

import com.revature.banking.models.Account;
import com.revature.banking.exceptions.ResourcePersistenceException;
import com.revature.banking.models.Account;
import com.revature.banking.util.ConnectionFactory;
import com.revature.banking.util.logging.Logger;

import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class AccountDao implements Crudable<Account> {

    private Logger logger = Logger.getLogger();

    @Override
    public Account create(Account newAccount) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            String sql = "insert into account values (default, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            // 1-indexed, so first ? starts are 1
            ps.setString(1, newAccount.getUsername());
            ps.setInt(2, newAccount.getAccountBalance());
            ps.setString(3, newAccount.getAccountType());


            int checkInsert = ps.executeUpdate();

            if (checkInsert == 0) {
                throw new ResourcePersistenceException("Account was not entered into database due to some issue.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return newAccount;
    }

    @Override
    public List<Account> findAll() throws IOException {
        List<Account> accounts = new LinkedList<>();

        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            String sql = "select * from account";
            Statement s = conn.createStatement();

            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                Account account = new Account();

                account.setUsername(rs.getString("username"));
                account.setAccountBalance(rs.getInt("account_balance"));
                account.setAccountType(rs.getString("account_type"));


                accounts.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return accounts;
    }

    @Override
    public Account findById(String id) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection();) { // try with resoruces, because Connection extends the interface Auto-Closeable

            String sql = "select * from account where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, Integer.parseInt(id));

            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                throw new ResourcePersistenceException("Account was not found in the database, please check ID entered was correct.");
            }

            Account account = new Account();
            account.setUsername(rs.getString("username"));
            account.setAccountBalance(rs.getInt("account_balance"));
            account.setAccountType(rs.getString("account_type"));


            return account;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean update(Account updatedAccount) {
        return false;
    }



    public void deposit(String amount, String id) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            String sql = "update account set account_balance=account_balance+? where username=?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(amount));
            ps.setInt(2, Integer.parseInt(id));
            int rs = ps.executeUpdate(); // remember dql, bc selects are the keywords

            System.out.println("Deposit of " + amount + " was successful");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void withdraw(String amount, String id) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            String sql = "update account set account_balance=account_balance-? where username=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(amount));
            ps.setInt(2, Integer.parseInt(id));
            int rs = ps.executeUpdate(); // remember dql, bc selects are the keywords

            System.out.println("Withdraw of " + amount + " was successful");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //@Override
    public boolean delete(String username) {
        return false;
    }


}







