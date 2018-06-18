/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reaktorlabs.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Paszternák László
 */
@Entity
@Table(name = "bank_transaction")
@XmlRootElement
public class BankTransaction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "amount")
    private Long amount;
    
    @Size(max = 100)
    @Column(name = "description")
    private String description;
    
    @Size(max = 100)
    @Column(name = "from_account_number")
    private String fromAccountNumber;
    
    @Size(max = 100)
    @Column(name = "to_account_number")
    private String toAccountNumber;
    
    @Column(name = "transaction_date")
    @Temporal(TemporalType.DATE)
    private Date transactionDate;
    
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private BankTransactionType type;
    
    @JoinColumn(name = "account_number_id", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Account accountNumberId;

    public BankTransaction() {
    }

    public BankTransaction(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFromAccountNumber() {
        return fromAccountNumber;
    }

    public void setFromAccountNumber(String fromAccountNumber) {
        this.fromAccountNumber = fromAccountNumber;
    }

    public String getToAccountNumber() {
        return toAccountNumber;
    }

    public void setToAccountNumber(String toAccountNumber) {
        this.toAccountNumber = toAccountNumber;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public BankTransactionType getType() {
        return type;
    }

    public void setType(BankTransactionType type) {
        this.type = type;
    }
    

    public Account getAccountNumberId() {
        return accountNumberId;
    }

    public void setAccountNumberId(Account accountNumberId) {
        this.accountNumberId = accountNumberId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof BankTransaction)) {
            return false;
        }
        BankTransaction other = (BankTransaction) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.reaktorlabs.entity.BankTransaction[ id=" + id + " ]";
    }
    
}
