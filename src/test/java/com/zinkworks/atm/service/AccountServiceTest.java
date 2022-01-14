package com.zinkworks.atm.service;


import com.zinkworks.atm.entity.Account;
import com.zinkworks.atm.repository.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

//import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {
    @Mock
    private AccountRepository accountRepository;
    @InjectMocks
    private AccountService underTest;

    @Test
    public void shouldGetAccount(){
        Account ac1 = new Account(34234564,9823,2450.33,600);
        when(accountRepository.findAccountByAccountNumber(ac1.getAccountNumber())).thenReturn(ac1);
        Account actual = underTest.getAccount(ac1.getAccountNumber());

        assertEquals(actual.getBalance(), ac1.getBalance(), 0.01);
        assertEquals(actual.getPin(), ac1.getPin(), 0.01);
        assertEquals(actual.getOverdraft(), ac1.getOverdraft(), 0.01);

    }

    @Test
    public void shouldCheckPin(){
        Boolean isSamePin = true;
        Account ac2 = new Account(56789,1006,2678.33,400);
        Account ac3 = new Account(34956,5789,56567,555);


        assertEquals(isSamePin,underTest.checkPin(ac2,ac2.getPin()));
        assertNotEquals(underTest.checkPin(ac2,ac3.getPin()), isSamePin );
    }

    @Test
    public void shouldCheckFundAvailability(){
        Account ac3 = new Account(56789,1006,2678.33,400);
        double expResult = ac3.getBalance() + ac3.getOverdraft();
        double actual = underTest.fundAvailability(ac3);
        assertEquals(expResult, actual,0.01);
    }

    @Test
    public void shouldCheckBalance(){
        Account ac4 = new Account(4556,3345,4432,444);
        double expBalance = ac4.getBalance();
        double actual = underTest.checkBalance(ac4);
        assertEquals(expBalance, actual,0.01);
    }

  /*  public void shouldUpdateBalance(){
        Account ac5 = new Account(5738295,4466,6834,440);
        double fundWidrawnCorrect = 834;
        double expected = ac5.getBalance() - fundWidrawnCorrect;
        underTest.updateBalance(ac5,fundWidrawnCorrect);
        assertEquals(expected, ac5.getBalance(),0.01);
    }*/
}
