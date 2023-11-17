package com.cartao.validacaodecartao.Service.validation;

import com.cartao.validacaodecartao.entity.CardTransaction;

import java.time.LocalDateTime;
import java.util.Optional;

public interface CardValidationService {
    boolean isOneWeekPassedValidation(String numeroCartao, LocalDateTime ultimaValidacao);

}
