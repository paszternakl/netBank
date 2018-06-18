/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reaktorlabs.controller;

import com.reaktorlabs.entity.Customer;
import com.reaktorlabs.logic.auth.Authentication;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;


/**
 *
 * @author Paszternák László
 */
@Named(value="loginController")
@RequestScoped
public class LoginController implements Serializable {

    private Authentication customerAuthenticationService;
    private Customer customer;

    public LoginController() {
    }
     
    @Inject
    private LoginController(Authentication customerAuthenticationService) {
        this.customerAuthenticationService = customerAuthenticationService;
        this.customer = new Customer();
    }
   
    public String loginCustomer() {
        customerAuthenticationService.login(customer);
        return "/balancedetails.xhtml";

    }
    public String logoutCustomer(){
        customerAuthenticationService.logout();
        return "/login.xhtml";
    }

    public String registerCustomer() {
        customerAuthenticationService.register(customer);
        return "/login.xhtml";
    }

    public Authentication getAuthentication() {
        return customerAuthenticationService;
    }

    public void setAuthentication(Authentication customerAuthenticationService) {
        this.customerAuthenticationService = customerAuthenticationService;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
