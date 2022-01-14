package com.zinkworks.atm.service;
import com.zinkworks.atm.entity.Banknote;
import com.zinkworks.atm.repository.AccountRepository;
import com.zinkworks.atm.repository.CashRepository;
import org.junit.Assert;
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
public class CashServiceTest {
    @Mock
    private CashRepository cashRepository;
    @InjectMocks
    private CashService underTest;

    @Test
    public void shouldGetAccount(){
        Banknote b1 = new Banknote(1,20,30,32,12);
        when(cashRepository.findBanknoteByNoteId(b1.getNoteId())).thenReturn(b1);
        Banknote actual = underTest.getBanknote(b1.getNoteId());

        assertEquals(actual.getFifty(), b1.getFifty(), 0.01);
        assertEquals(actual.getTwenty(), b1.getTwenty(), 0.01);
        assertEquals(actual.getTen(), b1.getTen(), 0.01);
        assertEquals(actual.getTotal(), b1.getTotal(), 0.01);

    }

    @Test
    public void shouldGetTotalFunds(){
        Banknote b3 = new Banknote(1,20,30,32,12);
        long expectedTotal  = b3.getTotal();
        long actualTotal = Math.round(underTest.getTotalFunds(b3));
        assertEquals(expectedTotal, actualTotal, 0.01);

    }

    @Test
    public void shouldUpdateNotes() {

        Banknote b3 = new Banknote(3,20,30,32,12);
        int count = 2;
        underTest.updateNotes(5, count, b3);
        underTest.updateNotes(10, count, b3);
        assertEquals( 18, b3.getFive(),0.01);
        assertEquals( 26, b3.getTen(),0.01);
    }

    @Test
    public void shouldWithdrawnNotes(){
        Banknote b4 = new Banknote(5,40,30,20,10);
        underTest.notesWithdrawn(b4,50);
        assertEquals(b4.getTotal(),550);
    }

}
