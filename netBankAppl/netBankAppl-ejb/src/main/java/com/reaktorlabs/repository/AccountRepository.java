/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reaktorlabs.repository;


import com.reaktorlabs.entity.Account;
import com.reaktorlabs.entity.BankTransaction;
import java.util.Date;
import java.util.List;

public abstract interface AccountRepository
{
  public void updateAccountByAccountNumber(String accountNumber);
 
  public void createBankAccount(Account account);
  
  public void removeAccounnt(Account account);
  
  public Account getAccountDetailsById(Long id);
  
  public List<BankTransaction> lastMonthTransactions(Long accountNumberId);
  
  public List<BankTransaction> statementOfAccount(Long accountNumberId,Date from, Date to);
  
  public List<BankTransaction> depositsOfAccount(Date from, Date to);
  
  public List<BankTransaction> withdrawalOfAccount(Date from, Date to);
  
  public void withdrawal(Long amount, Account account);  //levonás
  
  public void deposit(Long amount, Account account); //jóváírás
  
  public Boolean isUniqueBankAccountNumber(String bankAccountNumber);
  
  public List<Account> getAccountByCustomerId(Long id);
  
  public List<BankTransaction> getTransactionsByAccountNumberId(Long accountNumberId);
        
  public Account getAccountByNumber(String accountNumber);
}
