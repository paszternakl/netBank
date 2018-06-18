/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reaktorlabs.logic.accountservice;

import com.reaktorlabs.entity.Account;
import com.reaktorlabs.entity.BankTransaction;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Paszternák László
 */
public interface AccountService {
    
    public void createBankAccount(Account account);
    public void deleteAccount(Account account);
    public void updateAccount (String accountNumber);
    public void transferMoneyServices(Account fromBankAccount, Account toBankAccount, Long amount);
    public List<BankTransaction> getBankTransactions(Long accountNumberId,Date from,Date to);
    public List<BankTransaction> getDepositTransactions(Date from,Date to);
    public List<BankTransaction> getWithdrawalTransactions(Date from,Date to);
    public List<BankTransaction> lastMonthTransactions(Long accountNumberId);
    public String createBankAccountNumber();
    public List<Account> getAccountDetailsByCustomerId(Long id);
    public Account getAccountById(Long id);
    public List<BankTransaction> getTransactionsByAccountNumberId(Long accountNumberId);
    public Account getAccountByNumber(String accountNumber);

    
}
