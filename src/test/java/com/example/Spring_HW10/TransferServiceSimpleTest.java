package com.example.Spring_HW10;

import com.example.Spring_HW10.exceptions.AccountNotFoundException;
import com.example.Spring_HW10.model.Account;
import com.example.Spring_HW10.repositories.AccountRepository;
import com.example.Spring_HW10.services.TransferService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TransferServiceSimpleTest {

    @Mock
    public AccountRepository accountRepository;
    @InjectMocks
    public TransferService transferService;
// модульное тестирование
    @Test
    public void moneyTransferForDestinationAccountNotFoud() {
        //  pre
        Account sender = new Account();
        sender.setId(1);
        sender.setAmount(new BigDecimal(1000));

        given(accountRepository.findById(1L))
                .willReturn(Optional.of(sender));
        given(accountRepository.findById(2L))
                .willReturn(Optional.empty());

        //        @Test
        //    public void moneyTransferGoodTest(){
//        Account account1 = new Account();
//        account1.setId(1);
//        account1.setAmount(new BigDecimal(1000));
//        Account account2 = new Account();
//        account2.setId(2);
//        account2.setAmount(new BigDecimal(1000));

//        given(accountRepository.findById(account1.getId())).
//                willReturn(Optional.of(account1));
//        given(accountRepository.findById(account2.getId())).
//                willReturn(Optional.of(account2));

        //  action
        assertThrows(
        AccountNotFoundException.class,
        () -> transferService.transferMoney(1, 2, new BigDecimal(100))
        );

//        transferService.transferMoney(1, 2, new BigDecimal(50));

        //  check
        verify(accountRepository, never())
                .changeAmount(anyLong(), any());
//        verify(accountRepository).changeAmount(1, new BigDecimal(950));
//        verify(accountRepository).changeAmount(2, new BigDecimal(1050));
    }
}