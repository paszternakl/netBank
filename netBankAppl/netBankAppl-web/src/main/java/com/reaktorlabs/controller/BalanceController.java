/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reaktorlabs.controller;

import com.reaktorlabs.entity.Account;
import com.reaktorlabs.entity.BankTransaction;
import com.reaktorlabs.entity.Customer;
import com.reaktorlabs.logic.accountservice.AccountService;
import com.reaktorlabs.logic.customerservice.CustomerService;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Paszternák László
 */
@Named(value = "balanceController")
@ViewScoped
public class BalanceController implements Serializable {

    private List<BankTransaction> bankTransactions;
    private List<BankTransaction> filteredBankTransactions;
    private AccountService accountService;
    private CustomerService customerService;
    @Inject
    private HttpServletRequest servletRequest;
    private Customer customer;
    private Account account;
    private Date fromDate;
    private Date toDate;

    @Inject
    public BalanceController(AccountService accountService, CustomerService customerService) {
        this.accountService = accountService;
        this.customerService = customerService;
        this.bankTransactions = new ArrayList<>();
        this.customer = new Customer();
        this.account = new Account();

    }

    public BalanceController() {
    }

    @PostConstruct
    public void setCustomerById() {
        Long id = customerService.getCustomerIdByEmail(this.getCustomerEmailFromSession());
        customer = customerService.getCustomerById(id);

    }

    public String getCustomerEmailFromSession() {
        HttpSession mySession = servletRequest.getSession();
        System.out.println((String) mySession.getAttribute("customerEmail"));
        return (String) mySession.getAttribute("customerEmail");
    }

    public Account getAccountDetails() {
        Long id = customer.getId();
        List<Account> accounts = accountService.getAccountDetailsByCustomerId(id);
        return accounts.get(0);
    }

    public List<BankTransaction> getTransactionsByAccountIdLastMonth() {
        account = this.getAccountDetails();
        Long accountNumberId = account.getId();
        return bankTransactions = accountService.lastMonthTransactions(accountNumberId);
    }

    public List<BankTransaction> allBankTransactions() {
        account = this.getAccountDetails();
        Long accountNumberId = account.getId();
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        String fromDateString = params.get("form:fromdate_input");
        fromDate = this.dateConverter(fromDateString);
        String toDateString = params.get("form:todate_input");
        toDate = this.dateConverter(toDateString);
        return bankTransactions = accountService.getBankTransactions(accountNumberId, fromDate, toDate);
    }

    public Date dateConverter(String date) {
        DateFormat format = new SimpleDateFormat("yyyy.MM.dd", Locale.ENGLISH);
        Date newDate = new Date();
        try {
            newDate = format.parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(BalanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return newDate;
    }

    public List<BankTransaction> getBankTransactions() {
        return bankTransactions;
    }

    public void setBankTransactions(List<BankTransaction> bankTransactions) {
        this.bankTransactions = bankTransactions;
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

    public HttpServletRequest getServletRequest() {
        return servletRequest;
    }

    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String moveToHistory() {
        return "/history.xhtml";
    }

    public String moveToBalanceDetails() {
        return "/balancedetails.xhtml";
    }

    public String moveToTransfer() {
        return "/transfer.xhtml";
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Account getAccount() {
        return account;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public List<BankTransaction> getFilteredBankTransactions() {
        return filteredBankTransactions;
    }

    public void setFilteredBankTransactions(List<BankTransaction> filteredBankTransactions) {
        this.filteredBankTransactions = filteredBankTransactions;
    }

}
