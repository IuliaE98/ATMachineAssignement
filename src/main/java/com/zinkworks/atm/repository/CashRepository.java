package com.zinkworks.atm.repository;

import com.zinkworks.atm.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.zinkworks.atm.entity.Banknote;

@Repository
public interface CashRepository extends JpaRepository<Banknote, Integer>{
    public Banknote findBanknoteByNoteId(Integer noteId);
}
