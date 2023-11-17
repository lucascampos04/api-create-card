package com.cartao.validacaodecartao.Service.validation;

import com.cartao.validacaodecartao.entity.CardTransaction;
import com.cartao.validacaodecartao.repository.CardTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CardValidationServiceImpl implements CardValidationService {

    @Autowired
    private CardTransactionRepository cardTransactionRepository;

    @Override
    public boolean isOneWeekPassedValidation(String numeroCartao, LocalDateTime ultimaValidacao) {
        try {
            Optional<CardTransaction> ultimaTransacao = cardTransactionRepository.findTopByNumeroDoCartaoOrderByUltimaValidacaoDesc(numeroCartao);

            if (ultimaTransacao.isPresent()) {
                LocalDateTime umaSemanaAtras = LocalDateTime.now().minusWeeks(1);
                LocalDateTime ultimaValidacaoTransacao = ultimaTransacao.get().getUltimaValidacao();
                System.out.println("Cartão validado");
                return ultimaValidacaoTransacao == null || ultimaValidacaoTransacao.isBefore(umaSemanaAtras);
            }
        } catch (Exception e) {
            System.out.println("Erro ao validar o cartão: " + e.getMessage());
        }
        return true;
    }
}
