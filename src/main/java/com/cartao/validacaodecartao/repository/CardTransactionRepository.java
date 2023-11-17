package com.cartao.validacaodecartao.repository;

import com.cartao.validacaodecartao.entity.CardTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface CardTransactionRepository extends JpaRepository<CardTransaction, Long> {
}
