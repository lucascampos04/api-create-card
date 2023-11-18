package com.cartao.validacaodecartao.controller;

import com.cartao.validacaodecartao.Service.CardTransactionService;
import com.cartao.validacaodecartao.entity.CardTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/validate")
    public CardTransaction saveTransaction(@RequestBody CardTransaction cardTransaction){
        return cardTransactionService.saveTransaction(cardTransaction);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerCard(@RequestBody CardTransaction cardTransaction){
        try{
            CardTransaction saveTransaction = cardTransactionService.cadastrarCartao(cardTransaction);
            return ResponseEntity.ok("Cartão cadastrado com sucesso");
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body("Erro ao cadastrar o cartão : "+ e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable Long id){
        cardTransactionService.deleteTransaction(id);
    }

}
