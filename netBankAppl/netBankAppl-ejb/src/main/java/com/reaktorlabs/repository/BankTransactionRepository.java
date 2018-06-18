/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reaktorlabs.repository;


import com.reaktorlabs.entity.BankTransaction;

public interface BankTransactionRepository {
    
  public void create(BankTransaction transaction);  
  
  public BankTransaction getTransactionById(Long id);
  
  public void updateTransasction(BankTransaction bankTransaction);
  
}
