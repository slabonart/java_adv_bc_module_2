package pl.slabonart.java_adv_bc.module_2.task_5.models;

import java.math.BigDecimal;

public record ExchangeRate(
        String fromCurrency,
        String toCurrency,
        BigDecimal rate) {
}
