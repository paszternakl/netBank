/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reaktorlabs.logic.auth;

import com.reaktorlabs.entity.Customer;

/**
 *
 * @author Paszternák László
 */
public interface Authentication {

    void login(Customer customer);
    void logout();
    void register(Customer customer);
}
