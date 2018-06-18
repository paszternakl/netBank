/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reaktorlabs.logic.customerservice;

import com.reaktorlabs.entity.Customer;

/**
 *
 * @author Paszternák László
 */
public interface CustomerService{
    
    public void createCustomer(Customer customer);
    public void deleteCustomer(Customer customer);
    public void updateCustomer(Customer customer);
    public Customer getCustomerById(Long id);
    public Long getCustomerIdByEmail(String email);
    
    
}
