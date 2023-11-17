package com.cartao.validacaodecartao.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "numero_do_cartao")
    private String numeroDoCartao;
    @Column(name = "data_expiracao")
    private String dataDeExpiracao;

    @Column(name = "cvv")
    private String cvv;
    @Column(name = "valor_da_transacao")
    private BigDecimal valorDaTransacao;
    @Column(name = "moeda")
    private String moeda;
    @Column(name = "horario")
    private LocalDateTime horario;
    @Column(name = "status")
    private String status;
    private LocalDateTime ultimaValidacao;
}
