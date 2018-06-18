/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reaktorlabs.logic.util;

import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Paszternák László
 */
public class BankAccountNumberGenerator {
    
    public String bankAccountNumberGenerator(){
        String bankAccountNumber=null;
        for(int i=0;i<=2;i++){
            for(int j=0;j<8;j++){
                int digit=ThreadLocalRandom.current().nextInt(10);
            bankAccountNumber +=""+digit;
            }
            bankAccountNumber=bankAccountNumber+"-";
        }
        bankAccountNumber=bankAccountNumber.substring(4, 30);
        return bankAccountNumber;
    }

    
}
