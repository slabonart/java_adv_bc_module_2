package pl.slabonart.java_adv_bc.module_2.task_5.dao;

import pl.slabonart.java_adv_bc.module_2.task_5.models.Account;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class AccountDAO {

    private static final String ACCOUNT_DIR = "task_5/src/main/resources/accounts";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public synchronized void saveAccount(Account account) throws IOException {
        objectMapper.writeValue(new File(ACCOUNT_DIR, account.getId() + ".json"), account);
    }

    public synchronized Account loadAccount(String accountId) throws IOException {
        File file = new File(ACCOUNT_DIR, accountId + ".json");
        if (!file.exists()) {
            throw new IllegalArgumentException("Account not found: " + accountId);
        }
        return objectMapper.readValue(file, Account.class);
    }
}
