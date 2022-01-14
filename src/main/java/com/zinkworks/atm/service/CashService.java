package com.zinkworks.atm.service;

import com.zinkworks.atm.entity.Account;
import com.zinkworks.atm.entity.Banknote;
import com.zinkworks.atm.entity.Cash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zinkworks.atm.repository.CashRepository;

import java.util.ArrayList;

@Service
public class CashService {
	@Autowired
	CashRepository cashRepository;

	public Banknote getBanknote(Integer noteId){
		return cashRepository.findBanknoteByNoteId(noteId);
	}

	public void updateCash(Banknote banknote) {
		cashRepository.save(banknote);
	}

	public double getTotalFunds(Banknote banknote) {
		return banknote.getTotal();

	}

	public String notesWithdrawn(Banknote banknote, double amount) {
		String notes = "";
		ArrayList<Cash> cash = new ArrayList<>();
		cash.add(new Cash(5,banknote.getFive()));
		cash.add(new Cash(10,banknote.getTen()));
		cash.add(new Cash(20,banknote.getTwenty()));
		cash.add(new Cash(50,banknote.getFifty()));

		for(Cash c : cash){
			int count = 0;
			while(amount >= c.getNrOfEqualNotes() && c.getBanknotes() > 0){
				amount = amount - c.getNrOfEqualNotes();
				c.TakeNotes(1);
				count++;
				if(amount == 0)
					break;
			}
			notes += "Returned: " + count + " x " + c.getNrOfEqualNotes() + ", ";
			updateNotes(c.getNrOfEqualNotes(), count, banknote);
			if(amount == 0)
				break;
		}

		return notes;
	}

	public void updateNotes(int bill, int count, Banknote banknote) {
		switch(bill) {
			case 5: {
				banknote.setFive(banknote.getFive() - count);
				updateCash(banknote);
			}
			case 10: {
				banknote.setTen(banknote.getTen() - count);
				updateCash(banknote);
			}
			case 20: {
				banknote.setTwenty(banknote.getTwenty() - count);
				updateCash(banknote);
			}

			case 50: {
				banknote.setFifty(banknote.getFifty() - count);
				updateCash(banknote);
			}

		}
	}

	public Banknote insertDummyCash(Banknote b) {
		return cashRepository.save(b);
	}
}
