/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reaktorlabs.controller;

import com.reaktorlabs.entity.Account;
import com.reaktorlabs.logic.accountservice.AccountService;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Paszternák László
 */
@Named(value="accountController")
@RequestScoped
public class AccountController implements Serializable{
    
   
    private AccountService accountService;
    private Account account;

    public AccountController() {
    }
    
    @Inject
    public AccountController (AccountService accountService){
        this.accountService=accountService;
        this.account= new Account();
    }

    public AccountService getAccountService() {
        return accountService;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    public void createAccount(){
        accountService.createBankAccount(account);
        
    }
    

    
    
    
}
