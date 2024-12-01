package pl.slabonart.java_adv_bc.module_2.task_5.service;

import lombok.RequiredArgsConstructor;
import pl.slabonart.java_adv_bc.module_2.task_5.dao.AccountDAO;
import pl.slabonart.java_adv_bc.module_2.task_5.exceptions.InsufficientBalanceException;
import pl.slabonart.java_adv_bc.module_2.task_5.exceptions.InvalidExchangeRateException;
import pl.slabonart.java_adv_bc.module_2.task_5.models.Account;
import pl.slabonart.java_adv_bc.module_2.task_5.models.ExchangeRate;

import java.math.BigDecimal;
import java.util.Map;

@RequiredArgsConstructor
public class CurrencyExchangeService {

    private final AccountDAO accountDAO;

    private final Map<String, ExchangeRate> exchangeRates;

    public synchronized void exchangeCurrency(String accountId, String fromCurrency, String toCurrency, BigDecimal amount) throws Exception {

        Account account = accountDAO.loadAccount(accountId);

        BigDecimal fromBalance = account.getBalance(fromCurrency);

        if (fromBalance.compareTo(amount) < 0) {
            throw new InsufficientBalanceException("Not enough balance in " + fromCurrency);
        }

        String rateKey = fromCurrency + "->" + toCurrency;
        ExchangeRate rate = exchangeRates.get(rateKey);
        if (rate == null) {
            throw new InvalidExchangeRateException("Exchange rate not available for " + rateKey);
        }

        BigDecimal toAmount = amount.multiply(rate.rate());
        account.getBalances().put(fromCurrency, fromBalance.subtract(amount));
        account.getBalances().merge(toCurrency, toAmount, BigDecimal::add);

        accountDAO.saveAccount(account);
    }
}
