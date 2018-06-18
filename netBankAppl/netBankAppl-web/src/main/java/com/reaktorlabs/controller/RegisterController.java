/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reaktorlabs.controller;

import com.reaktorlabs.entity.Account;
import com.reaktorlabs.entity.Customer;
import com.reaktorlabs.logic.accountservice.AccountService;
import com.reaktorlabs.logic.auth.CustomerAuthenticationService;
import com.reaktorlabs.logic.customerservice.CustomerService;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Paszternák László
 */
@Named(value="registerController")
@RequestScoped
public class RegisterController implements Serializable{
    
    private CustomerService customersevice;
    private AccountService accountService;
    private final CustomerAuthenticationService customerAuthenticationService;
    private Account account;
    private Customer customer;

    @Inject 
    private HttpServletRequest servletRequest;
    
    @Inject
    public RegisterController(CustomerService customersevice, 
            AccountService accountService,CustomerAuthenticationService customerAuthenticationService) {
        this.customersevice = customersevice;
        this.accountService = accountService;
        this.customerAuthenticationService = customerAuthenticationService;
        this.account = new Account();
        this.customer = new Customer();
    }
    
    public CustomerService getCustomersevice() {
        return customersevice;
    }

    public void setCustomersevice(CustomerService customersevice) {
        this.customersevice = customersevice;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public HttpServletRequest getServletRequest() {
        return servletRequest;
    }

    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }
    
    public String registerCustomerAccount(){
        String password=customer.getPassword();
        customer=customersevice.getCustomerById(customersevice.getCustomerIdByEmail(customer.getEmail()));
        account.setCustomerId(customer);
        account.setBalance(0L);
        accountService.createBankAccount(account);
        customer.setPassword(password);
        customerAuthenticationService.register(customer);
        return "/login.xhtml";
    }
    @PostConstruct
    public void getCustomerEmailPasswordParam(){
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
        this.customer.setEmail(params.get("email"));
        this.customer.setPassword(params.get("password"));
       
    }
    public void validatePassword(ComponentSystemEvent event) {
	  FacesContext fc = FacesContext.getCurrentInstance();
	  UIComponent components = event.getComponent();
	  UIInput uiInputPassword = (UIInput)components.findComponent("password");
	  String password = uiInputPassword.getLocalValue() == null ? ""
		: uiInputPassword.getLocalValue().toString();
	  String passwordId = uiInputPassword.getClientId();
	  UIInput uiInputConfirmPassword = (UIInput) components.findComponent("confirmPassword");
	  String confirmPassword = uiInputConfirmPassword.getLocalValue() == null ? ""
		: uiInputConfirmPassword.getLocalValue().toString();
	  if (password.isEmpty() || confirmPassword.isEmpty()) {
		return;
	  }
	  if (!password.equals(confirmPassword)) {
		FacesMessage msg = new FacesMessage("A két jelszónak meg kell egyeznie!");
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		fc.addMessage(passwordId, msg);
		fc.renderResponse();		
	  }
	}   
}
