package com.wallet.service;

import com.wallet.constants.Constant;
import com.wallet.models.Account;
import com.wallet.models.User;
import com.wallet.models.Wallet;

import java.util.List;
import java.util.ArrayList;

public class WalletService {
    private final Wallet wallet;
    private final int topNNumber;
    public static final double INTEREST_AMOUNT = 10;
    public static final int NO_OF_TXN = 5;

    public WalletService(int topNNumber) {
        this.wallet = new Wallet();
        this.topNNumber = topNNumber;
    }

    public void createWallet(String userName, double initialBalance) {
        Account account = new Account(initialBalance);
        User user = new User(userName, account.getNumber());
        wallet.getUserList().add(user);
        wallet.getUsers().put(userName, user);
        wallet.getAccounts().put(account.getNumber(), account);
        System.out.println("Account Created for user : " + userName + " with initial balance : " + initialBalance);
    }

    public void transferMoney(String fromUser, String toUser, double amount) {
        if (!wallet.getUsers().containsKey(fromUser)) {
            System.out.println("The sender account is not exist");
            return;
        }
        if (!wallet.getUsers().containsKey(toUser)) {
            System.out.println("The receiver account is not exist");
            return;
        }
        if (amount < 0.0001) {
            System.out.println("Transaction is not allowed because the amount is less than 000.1 ");
            return;
        }
        int fromAccountNumber = wallet.getUsers().get(fromUser).getAccountNumber();
        int toAccountNumber = wallet.getUsers().get(toUser).getAccountNumber();
        Account fromAccount = wallet.getAccounts().get(fromAccountNumber);
        Account toAccount = wallet.getAccounts().get(toAccountNumber);

        if (!isAccountHaveSufficientBalance(fromAccountNumber, amount)) {
            return;
        }

        if (fromAccount.getBalance() >= amount) {
            fromAccount.setBalance(fromAccount.getBalance() - amount);
            if (fromAccount.getNoOfTransactionAfterFD() > -1) {
                fromAccount.setNoOfTransactionAfterFD(fromAccount.getNoOfTransactionAfterFD() + 1);
            }
        } else if ((fromAccount.getBalance() + fromAccount.getFdBalance()) >= amount) {
            fromAccount.setBalance(fromAccount.getBalance() + fromAccount.getFdBalance() - amount);
            fromAccount.setFdBalance(0.0);
            fromAccount.setNoOfTransactionAfterFD(-1);
        }
        toAccount.setBalance(toAccount.getBalance() + amount);
        fromAccount.addStatement(toUser + " debited " + amount);
        toAccount.addStatement(fromUser + " credited " + amount);
        fromAccount.setNoOfTransaction(fromAccount.getNoOfTransaction() + 1);
        toAccount.setNoOfTransaction(toAccount.getNoOfTransaction() + 1);

        if (fromAccount.getBalance() == toAccount.getBalance()) {
            fireOffer1(fromAccount, toAccount);
        }
        if (fromAccount.getNoOfTransactionAfterFD() == NO_OF_TXN && fromAccount.getBalance() > 0) {
            fromAccount.setBalance(fromAccount.getBalance() + INTEREST_AMOUNT);
            fromAccount.addStatement("Interest credited" + INTEREST_AMOUNT);
        }
    }

    private void fireOffer1(Account from, Account to) {
        System.out.println("Offer 1 fired");
        from.setBalance(from.getBalance() + 10);
        to.setBalance(to.getBalance() + 10);
        from.addStatement(Constant.OFFER1);
        to.addStatement(Constant.OFFER1);
    }

    public void getStatementByUserName(String userName) {
        if (!wallet.getUsers().containsKey(userName)) {
            System.out.println("User with name " + userName + " is not exist");
            return;
        }
        Account account = wallet.getAccounts().get(wallet.getUsers().get(userName).getAccountNumber());
        for (String statement : account.getStatements()) {
            System.out.println(statement);
        }
    }

    public void getOverview() {
        if (wallet.getUserList().size() == 0) {
            System.out.println("There is no user");
            return;
        }
        for (User user : wallet.getUserList()) {
            Account account = wallet.getAccounts().get(wallet.getUsers().get(user.getUserName()).getAccountNumber());
            System.out.print(user.getUserName() + " " + account.getBalance());
            if (account.getFdBalance() > 0) {
                System.out.print(" FD " + account.getFdBalance());
            }
            System.out.println();
        }
    }

    public void fireOffer2() {
        List<String> topUserNames = getTopNUserName();
        if (topUserNames.size() >= 1) {
            int accountNumber = wallet.getUsers().get(topUserNames.get(0)).getAccountNumber();
            Account account = wallet.getAccounts().get(accountNumber);
            account.setBalance(account.getBalance() + 10);
        }
        if (topUserNames.size() >= 2) {
            int accountNumber = wallet.getUsers().get(topUserNames.get(1)).getAccountNumber();
            Account account = wallet.getAccounts().get(accountNumber);
            account.setBalance(account.getBalance() + 5);
        }
        if (topUserNames.size() >= 3) {
            int accountNumber = wallet.getUsers().get(topUserNames.get(2)).getAccountNumber();
            Account account = wallet.getAccounts().get(accountNumber);
            account.setBalance(account.getBalance() + 2);
        }
        System.out.println("Offer 2 fired");
    }

    private List<String> getTopNUserName() {
        List<User> users = new ArrayList<>(wallet.getUserList());
        users.sort((o1, o2) -> {
            Account accountO1 = wallet.getAccounts().get(o1.getAccountNumber());
            Account accountO2 = wallet.getAccounts().get(o2.getAccountNumber());
            int indexOfO1 = wallet.getUserList().indexOf(o1);
            int indexOfO2 = wallet.getUserList().indexOf(o2);
            return accountO1.getNoOfTransaction() > accountO2.getNoOfTransaction() ? -1 :
                    accountO1.getNoOfTransaction() == accountO2.getNoOfTransaction() && accountO1.getBalance() > accountO2.getBalance() ? -1 :
                            accountO1.getNoOfTransaction() == accountO2.getNoOfTransaction() && accountO1.getBalance() == accountO2.getBalance() && indexOfO1 < indexOfO2 ? -1 : 0;
        });
        List<String> resultList = new ArrayList<>();
        for (User user : users) {
            resultList.add(user.getUserName());
            if (resultList.size() == topNNumber) {
                break;
            }
        }
        return resultList;
    }

    public void fixedDeposit(String userName, double amount) {
        if(isUserExist(userName)) {
            int accountNumber = wallet.getUsers().get(userName).getAccountNumber();
            if (isAccountHaveSufficientForFDBalance(accountNumber, amount)) {
                Account account = wallet.getAccounts().get(accountNumber);
                account.setFdBalance(amount);
                account.setBalance(account.getBalance() - amount);
                account.addStatement("debited for FD " + amount);
                account.setNoOfTransaction(account.getNoOfTransaction() + 1);
                account.setNoOfTransactionAfterFD(0);
                System.out.println("Added Money to FD");
            }
        }
    }

    private boolean isAccountHaveSufficientForFDBalance(int accountNumber, double amount) {
        if (isAccountExist(accountNumber)) {
            Account account = wallet.getAccounts().get(accountNumber);
            if (account.getBalance() < amount) {
                System.out.println("Account don't have sufficient balance to transfer");
                return false;
            }
        }
        return true;
    }

    private boolean isAccountHaveSufficientBalance(int accountNumber, double amount) {
        if (isAccountExist(accountNumber)) {
            Account account = wallet.getAccounts().get(accountNumber);
            if (account.getBalance() + account.getFdBalance() < amount) {
                System.out.println("Account don't have sufficient balance to transfer");
                return false;
            }
        }
        return true;
    }

    private boolean isAccountExist(int accountNumber) {
        if (!wallet.getAccounts().containsKey(accountNumber)) {
            System.out.println("Account is not exist");
            return false;
        }
        return true;
    }

    private boolean isUserExist(String userName) {
        if (!wallet.getUsers().containsKey(userName)) {
            System.out.println("User with name " + userName + " is not exist");
            return false;
        }
        return true;
    }
}
