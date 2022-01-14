package com.zinkworks.atm.entity;
import lombok.Data;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "account")
public class Account {

	@NotNull
	@Id
	@Column(name = "account_number", nullable = false)
    private long accountNumber;

	@Column(name = "pin", nullable = false)
    private int pin;

	@Column
    private double balance;

    @Column
    private double overdraft;

	private Account(){

	}

	public Account(long accountNumber, int pin, double balance, double overdraft) {
		this.accountNumber = accountNumber;
		this.pin = pin;
		this.balance = balance;
		this.overdraft = overdraft;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double openingBalance) {
		this.balance = openingBalance;
	}

	public double getOverdraft() {
		return overdraft;
	}

	public void setOverdraft(double overdraft) {
		this.overdraft = overdraft;
	}

	@Override
	public boolean equals(Object obj) {
		 if (this == obj) return true;
	        if (!(obj instanceof Account)) return false;
	        Account acc = (Account) obj;
	        return accountNumber == acc.getAccountNumber() && pin == acc.getPin() && balance == acc.getBalance() && overdraft == acc.getOverdraft();
	}

	 @Override
	    public int hashCode() {
	        return Objects.hash(getAccountNumber(), getPin(), getBalance(), getOverdraft());
	    }


    
}
