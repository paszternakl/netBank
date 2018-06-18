/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reaktorlabs.repository;

import com.reaktorlabs.entity.Customer;

public abstract interface CustomerRepository {

  public void create(Customer customer);
  
  public Customer update(Customer customer);
  
  public void delete(Customer customer);
  
  public Customer readCustomer(Long id);
  
  public Long getCustomerIdByEmail(String email);
}
