package pl.slabonart.java_adv_bc.module_2.task_5.threads;

import lombok.RequiredArgsConstructor;
import pl.slabonart.java_adv_bc.module_2.task_5.manager.AccountManager;
import pl.slabonart.java_adv_bc.module_2.task_5.utilities.LogUtil;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;

import static java.lang.Thread.sleep;

@RequiredArgsConstructor
public class CurrencyExchangeUpdater implements Runnable {

    private final AccountManager accountManager;
    private final String accountId;
    private final String currencyFrom;
    private final String currencyTo;

    @Override
    public void run() {
        while(true) {
            try {
                accountManager.performExchange(accountId, currencyFrom, currencyTo, getRandomAmount());
                sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                LogUtil.error(e.getMessage());
            }
        }
    }

    public static BigDecimal getRandomAmount() {
        return new BigDecimal(BigInteger.valueOf(new Random().nextInt(100001)), 2);
    }
}
