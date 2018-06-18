/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reaktorlabs.logic.auth;

import com.reaktorlabs.entity.Customer;
import com.reaktorlabs.logic.util.HashUtils;
import com.reaktorlabs.repository.CustomerRepository;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

/**
 *
 * @author Paszternák László
 */
public class CustomerAuthenticationService implements Authentication {

    private static final Logger LOGGER = Logger.getLogger(CustomerAuthenticationService.class.getName());

    @Inject
    private CustomerRepository customerRepository;

    @Inject
    private HttpServletRequest servletRequest;

    @Override
    public void login(Customer customer) {
        try {
            HttpSession customerSession = servletRequest.getSession();
            servletRequest.login(customer.getEmail(), customer.getPassword());
            customerSession.setAttribute("customerEmail", customer.getEmail());
            customerSession.setAttribute("customerId", customer.getId());
        } catch (ServletException ex) {
            LOGGER.warning(ex.getMessage());
        }
    }

    @Override
    public void logout() {
        try {
            servletRequest.getSession();
            servletRequest.logout();
        } catch (ServletException ex) {
            LOGGER.warning(ex.getMessage());
        }
    }
    @Transactional
    @Override
    public void register(Customer customer) {
        final String password = customer.getPassword();
        final String encryptedPassword = HashUtils.encryptSHA512(password);
        customer.setPassword(encryptedPassword);
        customerRepository.update(customer);
    }
}
