package com.cartao.validacaodecartao.Service;

import com.cartao.validacaodecartao.entity.CardTransaction;
import com.cartao.validacaodecartao.repository.CardTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardTransactionService {
    @Autowired
    private  CardTransactionRepository cardTransactionRepository;

    public List<CardTransaction> getAllTransactions(){
        return cardTransactionRepository.findAll();
    }
    public Optional<CardTransaction> getTransactionById(Long id){
       return cardTransactionRepository.findById(id);
    }
    public CardTransaction saveTransaction(CardTransaction cardTransaction){
        return cardTransactionRepository.save(cardTransaction);
    }

    public void deleteTransaction(Long id){
        cardTransactionRepository.deleteById(id);
    }
}
