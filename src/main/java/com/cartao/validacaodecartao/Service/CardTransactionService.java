package com.cartao.validacaodecartao.Service;

import com.cartao.validacaodecartao.Service.validation.CardValidationService;
import com.cartao.validacaodecartao.entity.CardTransaction;
import com.cartao.validacaodecartao.repository.CardTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CardTransactionService {
    @Autowired
    private CardTransactionRepository cardTransactionRepository;

    @Autowired
    private CardValidationService cardValidationService;


    public List<CardTransaction> getAllTransactions(){
        return cardTransactionRepository.findAll();
    }
    public Optional<CardTransaction> getTransactionById(Long id){
       return cardTransactionRepository.findById(id);
    }
    public CardTransaction saveTransaction(CardTransaction cardTransaction){
        if (cardValidationService.isOneWeekPassedValidation(cardTransaction.getNumeroDoCartao(), cardTransaction.getUltimaValidacao())){
            cardTransaction.setUltimaValidacao(LocalDateTime.now());
            return cardTransactionRepository.save(cardTransaction);
        } else {
            throw new RuntimeException("Apena uma validação por semana é permitido");
        }
    }

    public void deleteTransaction(Long id){
        cardTransactionRepository.deleteById(id);
    }
}
