/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reaktorlabs.logic.transactionservice;

import com.reaktorlabs.entity.Account;
import com.reaktorlabs.entity.BankTransactionType;
import java.util.Date;


/**
 *
 * @author Paszternák László
 */
public interface BankTransactionService {
    
    public void createDepositTransaction(
            BankTransactionType type,Long amount,String description,String fromAccountNumber,Account account,Date date);
    


    public void createWithdrawalTransaction(
            BankTransactionType type,Long amount,String description, String toAccountNumber, Account account,Date date);
}