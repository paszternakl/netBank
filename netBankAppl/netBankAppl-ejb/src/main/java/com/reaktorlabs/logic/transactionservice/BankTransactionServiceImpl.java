/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reaktorlabs.logic.transactionservice;

import com.reaktorlabs.entity.Account;
import com.reaktorlabs.entity.BankTransaction;
import com.reaktorlabs.entity.BankTransactionType;
import com.reaktorlabs.repository.BankTransactionRepository;
import java.util.Date;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Paszternák László
 */
@Stateless
public class BankTransactionServiceImpl implements BankTransactionService{
    
    private BankTransactionRepository bankTransactionRepository;
    
    
    @Inject
    public BankTransactionServiceImpl (BankTransactionRepository bankTransactionRepository){
        this.bankTransactionRepository=bankTransactionRepository;
    }

    public BankTransactionServiceImpl() {
        
    }

    @Override
    public void createDepositTransaction(
        BankTransactionType type,Long amount,String description,String FromAccountNumber, Account account,Date date){
        
        BankTransaction bankTransaction = new BankTransaction();
        bankTransaction.setType(type);
        bankTransaction.setAmount(amount);
        bankTransaction.setDescription(description);
        bankTransaction.setTransactionDate(date);
        bankTransaction.setFromAccountNumber(FromAccountNumber);
        bankTransaction.setAccountNumberId(account);
        bankTransactionRepository.updateTransasction(bankTransaction);   
    }

    @Override
    public void createWithdrawalTransaction(BankTransactionType type, Long amount, String description, String toAccountNumber, Account account,Date date){
        BankTransaction bankTransaction = new BankTransaction();
        bankTransaction.setType(type);
        bankTransaction.setAmount(amount);
        bankTransaction.setDescription(description);
        bankTransaction.setTransactionDate(date);
        bankTransaction.setToAccountNumber(toAccountNumber);
        bankTransaction.setAccountNumberId(account);
        bankTransactionRepository.updateTransasction(bankTransaction); 
    }
    
}
