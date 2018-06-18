/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reaktorlabs.controller;

import com.reaktorlabs.entity.Customer;
import com.reaktorlabs.logic.customerservice.CustomerService;
import com.reaktorlabs.logic.util.Mail;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;


/**
 *
 * @author Paszternák László
 */
@RequestScoped
@Named(value="customerController")
public class CustomerController implements Serializable{
   
    private CustomerService customerService;
    private Customer customer;
    private Mail mail;
    @Inject
    private HttpServletRequest servletRequest;
    
   @Inject
   public CustomerController(CustomerService customerService, Mail mail){
       this.customerService=customerService;
       this.customer=  new Customer();
       this.mail=mail;
   }

    public CustomerController (){}
   
   public String moveToPage1() {
      return "page1";
   }
   public String createCustomer(){
       customerService.createCustomer(customer);
       return "welcome";  
   }
    public String updateCustomerData() {
        customerService.updateCustomer(customer);
        return "balancedetail";
    }
    public String sendMail(){
        mail.send(customer.getEmail());
        return "/welcome.xhtml";
    }
    

    public CustomerService getCustomerService() {
        return customerService;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    public Mail getMail() {
        return mail;
    }

    public void setMail(Mail mail) {
        this.mail = mail;
    }
    
   

   
}
