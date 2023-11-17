package com.cartao.validacaodecartao.controller;

import com.cartao.validacaodecartao.Service.CardTransactionService;
import com.cartao.validacaodecartao.entity.CardTransaction;
import org.apache.catalina.authenticator.SavedRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transactions")
public class CardTransactionController {
    @Autowired
    private CardTransactionService cardTransactionService;

    @GetMapping
    public List<CardTransaction> getAllTransactions(){
        return cardTransactionService.getAllTransactions();
    }

    @GetMapping("/{id}")
    public Optional<CardTransaction> getTransactionById(@PathVariable Long id){
        return cardTransactionService.getTransactionById(id);
    }

    @PostMapping
    public CardTransaction saveTransaction(@RequestBody CardTransaction cardTransaction){
        return cardTransactionService.saveTransaction(cardTransaction);
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable Long id){
        cardTransactionService.deleteTransaction(id);
    }

}
