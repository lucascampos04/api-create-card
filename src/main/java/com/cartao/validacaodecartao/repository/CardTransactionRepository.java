package com.cartao.validacaodecartao.repository;

import com.cartao.validacaodecartao.entity.CardTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface CardTransactionRepository extends JpaRepository<CardTransaction, Long> {
    Optional<CardTransaction> findTopByNumeroDoCartaoOrderByUltimaValidacaoDesc(String numeroCartao);

    boolean existsByNumeroDoCartaoAndUltimaValidacaoIsNotNull(String numeroDoCartao);
}
