package com.zinkworks.atm.controller;

import com.zinkworks.atm.entity.Banknote;
import com.zinkworks.atm.entity.Cash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zinkworks.atm.entity.Account;
import com.zinkworks.atm.service.AccountService;
import com.zinkworks.atm.service.CashService;

@RestController
@RequestMapping("/atm")
public class ATMController {
	@Autowired
	private AccountService accountService;

	@Autowired
	private CashService cashService;

	@RequestMapping("account/saveAccount")
	public Account saveAccount(@RequestBody Account account) {
		return accountService.insertDummy(account);
	}

	@RequestMapping("banknote/saveBanknote")
	public Banknote saveBanknote(@RequestBody Banknote banknote) {
		return cashService.insertDummyCash(banknote);
	}

	@RequestMapping("banknote/{note_id}")
	public String getBanknote(@PathVariable Integer note_id) {
		return cashService.getBanknote(note_id) + "";
	}

	@RequestMapping("account/{account_number}/{pin}")
	public String checkBalance(@PathVariable Long account_number, @PathVariable Integer pin) {
		try {
			Account acc = accountService.getAccount(account_number);
			if (accountService.checkPin(acc, pin))
				return "your balance is: " + accountService.checkBalance(acc);
			return "PIN Incorrect";
		} catch (Exception e) {
			return "The combination of account number, PIN, is not registered in the system";
		}
	}
		@RequestMapping("account/{account_number}/{pin}/{amount}")
		public String withdraw(@PathVariable Long account_number, @PathVariable Integer pin, @PathVariable Double amount){
			try {

				Account ac1 = accountService.getAccount(account_number);
				Banknote banknote = cashService.getBanknote(0);
				if(accountService.checkPin(ac1, pin)) {
					if(amount <= (accountService.fundAvailability(ac1))) {
						if(amount <= cashService.getTotalFunds(banknote)) {
							String billsReturned = cashService.notesWithdrawn(banknote, amount);
							cashService.updateCash(banknote);
							accountService.updateBalance(ac1, amount);
							return billsReturned + "\n" + "Remaining funds: " + accountService.checkBalance(ac1);
						}
						return "The amount requested by you exceeds the ATM deposit ";
					}
					return "ATM out of cash";
				}
				return "PIN Incorrect";
			}
			catch(Exception e) {
				return "The combination of account number, PIN, is not registered in the system";
			}
		}


	
	
	
}
