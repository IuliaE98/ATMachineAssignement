package com.zinkworks.atm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zinkworks.atm.entity.Account;
import com.zinkworks.atm.repository.AccountRepository;

@Service
public class AccountService {
	@Autowired
	private AccountRepository accountRepository;

	public Account getAccount(Long accountNr) {
		return accountRepository.findAccountByAccountNumber(accountNr);
	}

	public boolean checkPin(Account a, int pin) {
		if (a.getPin() == pin)
			return true;
		return false;
	}
	
	public double checkBalance(Account a) {
		return a.getBalance();
	}

	public double fundAvailability(Account a) {
		return (a.getBalance() + a.getOverdraft());
	}

	public Account insertDummy(Account a) {
		return accountRepository.save(a);
	}

	public void updateBalance(Account a, double fundWidrawn) {
		if (a.getBalance() > fundWidrawn)
			a.setBalance(a.getBalance() - fundWidrawn);
		else {
			double availablefunds = a.getBalance() + a.getOverdraft();
			a.setOverdraft(availablefunds - fundWidrawn);
			a.setBalance(0);

		}
		accountRepository.save(a);
	}
}
