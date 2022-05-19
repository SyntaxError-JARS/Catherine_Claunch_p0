package com.revature.banking.models;

public class Account {

    private String username;
    private int accountBalance;
    private String accountType;


    // This is a No-Args Constructor. IT's the default, IFFFl there is no other constructor added.
    // Otherwise, the custom constructor overwrites
    public Account(){
        super();
    }

    public Account(String username, int accountBalance, String accountType){
        super();
        this.username = username; // shadowing, with provided arguments
        this.accountBalance = accountBalance;
        this.accountType = accountType;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAccountBalance(){
        return this.accountBalance;
    }

    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }



    @Override
    public String toString() {
        return "Account{" +
                "username='" + username + '\'' +
                ", accountBalance='" + accountBalance + '\'' +
                ", accountType='" + accountType + '\'' +
                '}';
    }
}