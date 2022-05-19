package com.revature.banking.services;

import com.revature.banking.daos.AccountDao;
import com.revature.banking.models.Account;
import com.revature.banking.models.User;
import com.revature.banking.util.logging.Logger;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AccountServices implements Serviceable<Account> {
    private final AccountDao accountDao;

    private Logger logger = Logger.getLogger();

    public AccountServices(AccountDao accountDao){
        this.accountDao = accountDao;


    }

    @Override
    public Account create(Account newAccount) {
        return accountDao.create(newAccount);
    }

    @Override
    public List<Account> readAll() {
        try {
            return accountDao.findAll();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Account readById(String id) {
        return accountDao.findById(id);
    }

    @Override
    public Account update(Account updatedAccount) {
        return null;
    }

    public void deposit(String value, String id) {
        accountDao.deposit(value, id);
    }

    public void withdraw(String value, String id) {

        accountDao.withdraw(value, id);
    }

    public boolean deleteAccount(String id) throws SQLException {
        Logger logger = null;
        try {
            accountDao.findById(id);
            return accountDao.delete(id);
        } catch (Exception e) {
            logger.warn("Account was not found");
            return false;
        }
    }

    @Override
    public boolean validateInput(Account object) {
        return false;
    }
}