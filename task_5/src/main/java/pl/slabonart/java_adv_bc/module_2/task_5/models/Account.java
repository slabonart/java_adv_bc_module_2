package pl.slabonart.java_adv_bc.module_2.task_5.models;

import lombok.Getter;
import java.math.BigDecimal;
import java.util.Map;

@Getter
public class Account {

    private String id;
    private String ownerName;
    private Map<String, BigDecimal> balances;

    public Account() {
    }

    public Account(String id, String ownerName, Map<String, BigDecimal> balances) {
        this.id = id;
        this.ownerName = ownerName;
        this.balances = balances;
    }

    public synchronized BigDecimal getBalance(String currencyCode) {
        return balances.getOrDefault(currencyCode, BigDecimal.ZERO);
    }

    public synchronized void updateBalance(String currencyCode, BigDecimal amount) {
        balances.put(currencyCode, getBalance(currencyCode).add(amount));
    }
}

