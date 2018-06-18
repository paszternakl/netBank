/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reaktorlabs.controller;

import com.reaktorlabs.entity.Account;
import com.reaktorlabs.entity.BankTransaction;
import com.reaktorlabs.entity.BankTransactionType;
import com.reaktorlabs.entity.Customer;
import com.reaktorlabs.logic.accountservice.AccountService;
import com.reaktorlabs.logic.customerservice.CustomerService;
import com.reaktorlabs.logic.transactionservice.BankTransactionService;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import javax.transaction.Transactional;

/**
 *
 * @author Paszternák László
 */
@ViewScoped
@Named(value = "banktransactionController")
public class BankTransactionController implements Serializable {

    private BalanceController balanceController;
    private AccountService accountService;
    private final BankTransaction banktransaction;
    private CustomerService customerService;
    private final BankTransactionService bankTransactionService;
    private Account accountFrom;
    private Account accountTo;
    private Customer customer;
    private String accountToNumber;
    private Long amount;

    @Inject
    private HttpServletRequest servletRequest;

    @Inject
    public BankTransactionController(BalanceController balanceController, AccountService accountService,
             CustomerService customerService, BankTransactionService bankTransactionService) {
        this.balanceController = balanceController;
        this.accountService = accountService;
        this.customerService = customerService;
        this.bankTransactionService = bankTransactionService;
        this.banktransaction = new BankTransaction();
        this.accountTo = new Account();
    }

    @Transactional
    public String transferMoney() {
        accountFrom = this.getAccountByCustomerEmail();
        String accountFromNumber = accountFrom.getNumber();
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        accountToNumber = params.get("transferform:accountnumber");
        String amountString = params.get("transferform:amount");
        String description = params.get("transferform:description");
        String transferDate = params.get("transferform:date_input");
        Date convertedTransferDate = balanceController.dateConverter(transferDate);
        accountTo = accountService.getAccountByNumber(accountToNumber);
        amount = Long.parseLong(amountString);
        accountService.transferMoneyServices(accountFrom, accountTo, amount);
        bankTransactionService.createDepositTransaction(BankTransactionType.JÓVÁÍRÁS, amount, description, accountFromNumber, accountTo, convertedTransferDate);
        bankTransactionService.createWithdrawalTransaction(BankTransactionType.TERHELÉS, amount, description, accountToNumber, accountFrom, convertedTransferDate);
        return this.moveToBalanceDetails();

    }

    public BankTransaction getBanktransaction() {
        return banktransaction;
    }

    public BalanceController getBalanceController() {
        return balanceController;
    }

    public void setBalanceController(BalanceController balanceController) {
        this.balanceController = balanceController;
    }

    public AccountService getAccountService() {
        return accountService;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    public CustomerService getCustomerService() {
        return customerService;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public Account getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(Account accountFrom) {
        this.accountFrom = accountFrom;
    }

    public Account getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(Account accountTo) {
        this.accountTo = accountTo;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getAccountToNumber() {
        return accountToNumber;
    }

    public void setAccountToNumber(String accountToNumber) {
        this.accountToNumber = accountToNumber;
    }

    public HttpServletRequest getServletRequest() {
        return servletRequest;
    }

    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String moveToBalanceDetails() {
        return "/balancedetails.xhtml";
    }

    private Account getAccountByCustomerEmail() {
        Long id = customerService.getCustomerIdByEmail(balanceController.getCustomerEmailFromSession());
        customer = customerService.getCustomerById(id);
        List<Account> accounts = accountService.getAccountDetailsByCustomerId(customer.getId());
        return accounts.get(0);
    }

}
