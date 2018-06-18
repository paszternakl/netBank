/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reaktorlabs.logic.accountservice;

import com.reaktorlabs.entity.Account;
import com.reaktorlabs.entity.BankTransaction;
import com.reaktorlabs.logic.util.BankAccountNumberGenerator;
import com.reaktorlabs.repository.AccountRepository;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Paszternák László
 */
@Stateless
public class AccountServicesImpl implements AccountService {

    private AccountRepository accountRepository;
    private BankAccountNumberGenerator accountNumberGenerator;

    public AccountServicesImpl() {
    }

    @Inject
    public AccountServicesImpl(AccountRepository accountRepository) {
        this.accountNumberGenerator = new BankAccountNumberGenerator();
        this.accountRepository = accountRepository;

    }

    @Override
    public void deleteAccount(Account account) {
        accountRepository.removeAccounnt(account);
    }

    @Override
    public void updateAccount(String accountNumber) {
        accountRepository.updateAccountByAccountNumber(accountNumber);
    }

    @Override
    public void transferMoneyServices(Account fromBankAccount, Account toBankAccount, Long amount) {
        accountRepository.withdrawal(amount, fromBankAccount);
        accountRepository.deposit(amount, toBankAccount);
    }

    @Override
    public List<BankTransaction> getBankTransactions(Long accountNumberId,Date from, Date to) {
        return accountRepository.statementOfAccount(accountNumberId,from, to);
    }

    @Override
    public List<BankTransaction> getDepositTransactions(Date from, Date to) {
        return accountRepository.depositsOfAccount(from, to);
    }

    @Override
    public List<BankTransaction> getWithdrawalTransactions(Date from, Date to) {
        return accountRepository.withdrawalOfAccount(from, to);
    }

    @Override
    public String createBankAccountNumber() {
        String bankAccount = accountNumberGenerator.bankAccountNumberGenerator();
        while (!accountRepository.isUniqueBankAccountNumber(bankAccount)) {
            bankAccount = accountNumberGenerator.bankAccountNumberGenerator();
        }
        return bankAccount;
    }

    @Override
    public void createBankAccount(Account account) {
        accountRepository.createBankAccount(account);
    }

    @Override
    public List<BankTransaction> lastMonthTransactions(Long accountNumberId) {
        return accountRepository.lastMonthTransactions(accountNumberId);
    }

    @Override
    public List<Account> getAccountDetailsByCustomerId(Long id) {
        return accountRepository.getAccountByCustomerId(id);
    }

    @Override
    public Account getAccountById(Long id) {
        return accountRepository.getAccountDetailsById(id);
    }

    @Override
    public List<BankTransaction> getTransactionsByAccountNumberId(Long accountNumberId) {
        return accountRepository.getTransactionsByAccountNumberId(accountNumberId);
    }

    @Override
    public Account getAccountByNumber(String accountNumber) {
        return accountRepository.getAccountByNumber(accountNumber);
    }

}
