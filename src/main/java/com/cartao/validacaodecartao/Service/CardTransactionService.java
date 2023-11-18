package com.cartao.validacaodecartao.Service;

import com.cartao.validacaodecartao.Service.validation.CardValidationService;
import com.cartao.validacaodecartao.entity.CardTransaction;
import com.cartao.validacaodecartao.exception.ValidacaoExcpetion;
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

    public List<CardTransaction> getAllTransactions() {
        return cardTransactionRepository.findAll();
    }

    public Optional<CardTransaction> getTransactionById(Long id) {
        return cardTransactionRepository.findById(id);
    }

    public CardTransaction saveTransaction(CardTransaction cardTransaction) {
        try {
            if (cardValidationService.isOneWeekPassedValidation(cardTransaction.getNumeroDoCartao(), cardTransaction.getUltimaValidacao())) {
                cardTransaction.setUltimaValidacao(LocalDateTime.now());
                CardTransaction savedTransaction = cardTransactionRepository.save(cardTransaction);
                System.out.println("Foi");
                return savedTransaction;
            } else {
                throw new ValidacaoExcpetion("Apenas uma validação por semana é permitida || Caso esteja tentando cadastrar um cartão a url correta é : /register");
            }
        } catch (ValidacaoExcpetion e) {
            System.out.println("Erro de validação: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.out.println("Erro ao salvar a transação: " + e.getMessage());
            return null;
        }
    }

    public CardTransaction cadastrarCartao(CardTransaction cardTransaction) {
        try {
            CardTransaction savedTransaction = cardTransactionRepository.save(cardTransaction);
            System.out.println("Cartão cadastrado com sucesso");
            return savedTransaction;
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar o cartão: " + e.getMessage());
            return null;
        }
    }

    public void deleteTransaction(Long id) {
        cardTransactionRepository.deleteById(id);
    }
}
