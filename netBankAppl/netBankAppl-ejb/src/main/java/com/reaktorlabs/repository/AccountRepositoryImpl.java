/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reaktorlabs.repository;

import com.reaktorlabs.entity.Account;
import com.reaktorlabs.entity.BankTransaction;
import com.reaktorlabs.entity.BankTransactionType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import org.joda.time.DateTime;

public class AccountRepositoryImpl implements AccountRepository {

    @PersistenceContext(name = "netBankPU")
    private EntityManager entityManager;

    @Override
    public void updateAccountByAccountNumber(String accountNumber) {
        entityManager.merge(entityManager.find(Account.class, accountNumber));
    }

    @Override
    public void removeAccounnt(Account account) {
        entityManager.remove(account);
    }

    @Override
    public Account getAccountDetailsById(Long id) {
        return entityManager.find(Account.class, id);
    }

    @Override
    public List<BankTransaction> lastMonthTransactions(Long accountNumberId) {
        Date date = new Date();
        Date daysAgo = new DateTime(date).minusDays(30).toDate();
        List<BankTransaction> transactions = entityManager
                .createQuery("SELECT t FROM BankTransaction t WHERE t.accountNumberId.id= :id AND t.transactionDate BETWEEN :from AND :to")
                .setParameter("id", accountNumberId)
                .setParameter("from", daysAgo, TemporalType.DATE)
                .setParameter("to", new Date(), TemporalType.DATE)
                .getResultList();
        return transactions;
    }

    @Override
    public List<BankTransaction> statementOfAccount(Long accountNumberId, Date from, Date to) {
        List<BankTransaction> transactions = entityManager
                .createQuery("SELECT t FROM BankTransaction t WHERE t.accountNumberId.id= :id AND t.transactionDate BETWEEN :from AND :to")
                .setParameter("id", accountNumberId)
                .setParameter("from", from, TemporalType.DATE)
                .setParameter("to", to, TemporalType.DATE)
                .getResultList();
        return transactions;
    }

    @Override
    public List<BankTransaction> depositsOfAccount(Date from, Date to) {
        List<BankTransaction> deposiTransactions = new ArrayList();

        List<BankTransaction> transactions = entityManager
                .createQuery("SELECT t FROM BankTransaction t WHERE t.transactionDate BETWEEN :from AND :to")
                .setParameter("from", from, TemporalType.DATE)
                .setParameter("to", to, TemporalType.DATE)
                .getResultList();
        for (BankTransaction transaction : transactions) {
            if (transaction.getType().equals(BankTransactionType.TERHELÉS)) {
                deposiTransactions.add(transaction);
            }
        }
        return deposiTransactions;
    }

    @Override
    public List<BankTransaction> withdrawalOfAccount(Date from, Date to) {
        List<BankTransaction> withdrawalTransactions = new ArrayList();

        List<BankTransaction> transactions = entityManager
                .createQuery("SELECT t FROM BankTransaction t WHERE t.transactionDate BETWEEN :from AND :to")
                .setParameter("from", from, TemporalType.DATE)
                .setParameter("to", to, TemporalType.DATE)
                .getResultList();
        for (BankTransaction transaction : transactions) {
            if (transaction.getType().equals(BankTransactionType.JÓVÁÍRÁS)) {
                withdrawalTransactions.add(transaction);
            }
        }
        return withdrawalTransactions;
    }

    @Override
    public void withdrawal(Long amount, Account account) {
        account.setBalance(account.getBalance() - amount);
        entityManager.merge(account);
    }

    @Override
    public void deposit(Long amount, Account account) {
        account.setBalance(account.getBalance() + amount);
        entityManager.merge(account);
    }

    @Override
    public void createBankAccount(Account account) {
        Date date = new Date();
        account.setOpenDate(date);
        entityManager.merge(account);
    }

    @Override
    public Boolean isUniqueBankAccountNumber(String bankAccountNumber) {
        return entityManager.find(Account.class, bankAccountNumber) == null;
    }

    @Override
    public List<Account> getAccountByCustomerId(Long customerId) {
        TypedQuery<Account> accounts = entityManager
                .createQuery("SELECT a FROM Account a WHERE a.customerId.id = :customerId", Account.class);
        accounts.setParameter("customerId", customerId);
        return accounts.getResultList();
    }

    @Override
    public List<BankTransaction> getTransactionsByAccountNumberId(Long accountNumberId) {
        List<BankTransaction> transactions = entityManager
                .createQuery("SELECT t FROM BankTransaction t WHERE t.accountNumberId.id= :id", BankTransaction.class)
                .setParameter("id", accountNumberId)
                .getResultList();
        return transactions;
    }
    @Override
    public Account getAccountByNumber(String number){
        TypedQuery<Account> query=entityManager.createQuery("SELECT a FROM Account a WHERE a.number = :number",Account.class)
        .setParameter("number", number);
        return query.getSingleResult();
  

    }

}
