package com.cartao.validacaodecartao.Service.validation;

import com.cartao.validacaodecartao.entity.CardTransaction;
import com.cartao.validacaodecartao.exception.ValidacaoExcpetion;
import com.cartao.validacaodecartao.repository.CardTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CardValidationServiceImpl {

    @Autowired
    private CardTransactionRepository cardTransactionRepository;

    @Override
    public boolean isOneWeekPassedValidation(String numeroCartao, LocalDateTime ultimaValidacao) {
        try {
            Optional<CardTransaction> ultimaTransacao = cardTransactionRepository.findTopByNumeroDoCartaoOrderByUltimaValidacaoDesc(numeroCartao);

            if (ultimaTransacao.isPresent()) {
                CardTransaction ultimaTransacaoCartao = ultimaTransacao.get();

                if ("Verificado".equals(ultimaTransacaoCartao.getStatus())) {
                    throw new ValidacaoExcpetion("Erro, cartão já verificado, tente novamente daqui uma semana");
                }

                if (ultimaTransacaoCartao.getUltimaValidacao() != null) {
                    LocalDateTime umaSemanaAtras = LocalDateTime.now().minusWeeks(1);
                    LocalDateTime ultimaValidacaoTransacao = ultimaTransacaoCartao.getUltimaValidacao();

                    if (ultimaValidacaoTransacao.isAfter(umaSemanaAtras)) {
                        return true;
                    }
                }
            }

        } catch (Exception ex) {
            throw new ValidacaoExcpetion("Erro ao validar o cartão: " + ex.getMessage());
        }
        return false;
    }
}
