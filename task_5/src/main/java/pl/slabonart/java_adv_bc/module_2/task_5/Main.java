package pl.slabonart.java_adv_bc.module_2.task_5;

import pl.slabonart.java_adv_bc.module_2.task_5.dao.AccountDAO;
import pl.slabonart.java_adv_bc.module_2.task_5.manager.AccountManager;
import pl.slabonart.java_adv_bc.module_2.task_5.models.Account;
import pl.slabonart.java_adv_bc.module_2.task_5.models.ExchangeRate;
import pl.slabonart.java_adv_bc.module_2.task_5.service.CurrencyExchangeService;
import pl.slabonart.java_adv_bc.module_2.task_5.threads.CurrencyExchangeUpdater;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.util.concurrent.Executors.newFixedThreadPool;

public class Main {

    public static void main(String[] args) throws Exception {

        AccountDAO accountDAO = new AccountDAO();

        Account testAccount = createTestAccount(accountDAO);

        AccountManager accountManager = getAccountManager(accountDAO);


        ExecutorService executorService = newFixedThreadPool(3);
        List<Callable<Object>>  tasks = List.of(
                Executors.callable(new CurrencyExchangeUpdater(accountManager, testAccount.getId(), "USD", "EUR")),
                Executors.callable(new CurrencyExchangeUpdater(accountManager, testAccount.getId(), "EUR", "GBP")),
                        Executors.callable(new CurrencyExchangeUpdater(accountManager, testAccount.getId(), "GBP", "USD"))
        );

        executorService.invokeAll(tasks);
    }

    private static AccountManager getAccountManager(AccountDAO accountDAO) {
        Map<String, ExchangeRate> rates = new HashMap<>();
        rates.put("USD->EUR", new ExchangeRate("USD", "EUR", new BigDecimal("0.94")));
        rates.put("EUR->USD", new ExchangeRate("EUR", "USD", new BigDecimal("1.05")));
        rates.put("USD->GBP", new ExchangeRate("USD", "BBP", new BigDecimal("0.78")));
        rates.put("GBP->USD", new ExchangeRate("GBP", "USD", new BigDecimal("1.27")));
        rates.put("GBP->EUR", new ExchangeRate("GBP", "EUR", new BigDecimal("1.20")));
        rates.put("EUR->GBP", new ExchangeRate("EUR", "GBP", new BigDecimal("0.83")));
        CurrencyExchangeService exchangeService = new CurrencyExchangeService(accountDAO, rates);

        AccountManager accountManager = new AccountManager(exchangeService);
        return accountManager;
    }

    private static Account createTestAccount(AccountDAO accountDAO) throws IOException {

        Map<String, BigDecimal> balances = new ConcurrentHashMap<>();
        balances.put("USD", new BigDecimal("1000"));
        balances.put("EUR", new BigDecimal("1000"));
        balances.put("GBP", new BigDecimal("1000"));

        Account account = new Account("1", "John Doe", balances);
        accountDAO.saveAccount(account);
        return account;
    }


}