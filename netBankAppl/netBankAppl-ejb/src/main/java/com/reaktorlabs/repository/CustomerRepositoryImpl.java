/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reaktorlabs.repository;


import com.reaktorlabs.entity.Customer;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Paszternák László
 */
public class CustomerRepositoryImpl implements CustomerRepository{

    @PersistenceContext(name = "netBankPU")
    private EntityManager entityManager;
    
    @Override
    public void create(Customer customer) {
        entityManager.persist(customer);
    }

    @Override
    public Customer update(Customer customer) {
        return entityManager.merge(customer);
        
    }

    @Override
    public void delete(Customer customer) {
        entityManager.remove(customer);
        
    }

    @Override
    public Customer readCustomer(Long id) {
        return entityManager.find(Customer.class, id);
        
    }

    @Override
    public Long getCustomerIdByEmail(String email){
        Customer customer=entityManager.createQuery("SELECT c FROM Customer c WHERE c.email = :email",Customer.class)
                .setParameter("email", email).getSingleResult();
        return customer.getId();
    }

    

    
}
