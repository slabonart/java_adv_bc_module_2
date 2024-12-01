package pl.slabonart.java_adv_bc.module_2.task_5.manager;

import lombok.RequiredArgsConstructor;
import pl.slabonart.java_adv_bc.module_2.task_5.service.CurrencyExchangeService;
import pl.slabonart.java_adv_bc.module_2.task_5.utilities.LogUtil;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class AccountManager {

    private final CurrencyExchangeService exchangeService;

    public void performExchange(String accountId, String fromCurrency, String toCurrency, BigDecimal amount) {
        try {
            exchangeService.exchangeCurrency(accountId, fromCurrency, toCurrency, amount);
            LogUtil.log("Successfully exchanged " + amount + " " + fromCurrency + " to " + toCurrency + " for account " + accountId);
        } catch (Exception e) {
            LogUtil.log("Error during currency exchange: " + e.getMessage());
        }
    }
}
