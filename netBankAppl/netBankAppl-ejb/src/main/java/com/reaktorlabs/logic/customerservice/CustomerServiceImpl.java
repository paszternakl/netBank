/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reaktorlabs.logic.customerservice;

import com.reaktorlabs.entity.Customer;
import com.reaktorlabs.repository.CustomerRepository;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Paszternák László
 */
@Stateless
public class CustomerServiceImpl implements CustomerService {
    

    private CustomerRepository customerRepository;


    public CustomerServiceImpl() {
    }
    @Inject
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;

    }


    @Override
    public void createCustomer(Customer customer) {
        customerRepository.create(customer);
    }

    @Override
    public void deleteCustomer(Customer customer) {
        customerRepository.delete(customer);
    }

    @Override
    public void updateCustomer(Customer customer) {
        customerRepository.update(customer);
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.readCustomer(id);
    }

    @Override
    public Long getCustomerIdByEmail(String email) {
        return customerRepository.getCustomerIdByEmail(email);
    }
    


}
