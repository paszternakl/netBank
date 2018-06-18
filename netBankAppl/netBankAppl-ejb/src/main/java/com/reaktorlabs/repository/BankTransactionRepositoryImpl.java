/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reaktorlabs.repository;

import com.reaktorlabs.entity.BankTransaction;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Paszternák László
 */
public class BankTransactionRepositoryImpl implements BankTransactionRepository{
    
    
    @PersistenceContext(name = "netBankPU")
    private EntityManager entityManager;
    
    @Override
    public void create(BankTransaction transaction) {
        entityManager.persist(transaction);
        
    }

    @Override
    public BankTransaction getTransactionById(Long id) {
      return  entityManager.find(BankTransaction.class, id);
    }

    @Override
    public void updateTransasction(BankTransaction bankTransaction) {
        entityManager.merge(bankTransaction);
    }
    
}
