package com.zinkworks.atm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.zinkworks.atm.entity.Account;

@Repository
public interface AccountRepository  extends JpaRepository<Account, Integer>{
	 public Account findAccountByAccountNumber(Long accountNumber);

}
