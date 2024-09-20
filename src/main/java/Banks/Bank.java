package Banks;

import Banks.Accounts.Account;
import Banks.Accounts.CreditAccount;
import Banks.Accounts.DebitAccount;
import Banks.Accounts.IAccount;
import Banks.Clients.Client;
import Exceptions.IncorrectBankSettingsException;
import Observer.EventManager;

import java.util.*;

import static Observer.EventType.UpdateInterest;
import static Observer.EventType.UpdateTransactionLimit;

public class Bank {
    private final BankSettings bankSettings;
    private EventManager eventManager;
    private final Map<UUID, IAccount> accounts;
    private final List<Client> clients = new ArrayList<>();

    public Bank(BankSettings bankSettings) {
        this.bankSettings = bankSettings;
        if (bankSettings == null) {
            throw new IncorrectBankSettingsException();
        }
        this.accounts = new HashMap<>();
    }

    public void chargeInterest() {
        for (Map.Entry<UUID, IAccount> entry : accounts.entrySet()) {
            IAccount account = entry.getValue();
            account.addInterest();
        }
    }

    public void chargeCommission() {
        for (Map.Entry<UUID, IAccount> entry : accounts.entrySet()) {
            IAccount account = entry.getValue();
            account.addUseCommission();
        }
    }

    public void calculateInterests() {
        for (Map.Entry<UUID, IAccount> entry : accounts.entrySet()) {
            IAccount account = entry.getValue();
            account.calculateInterest();
        }
    }

    public void updateInterest(int newInterest) {
        for (Map.Entry<UUID, IAccount> entry : accounts.entrySet()) {
            IAccount account = entry.getValue();
            account.updateInterest(newInterest);
            eventManager.Notify(UpdateInterest, "Interest was updated");
        }
    }
    public void updateTransactionLimit(Double newTransactionLimit) {
        for (Map.Entry<UUID, IAccount> entry : accounts.entrySet()) {
            IAccount account = entry.getValue();
            account.updateTransactionLimit(newTransactionLimit);
            eventManager.Notify(UpdateTransactionLimit, "Transactions limit was updated");
        }
    }

    public HashMap<UUID, IAccount> getAccounts() {
        return (HashMap<UUID, IAccount>) accounts;
    }
    public Account createDebitAccount(UUID clientId) {
        UUID accountId = UUID.randomUUID();
        Account account = new DebitAccount(accountId, clientId,0.0, bankSettings.getAnnualInterest(), bankSettings.getTransactionsLimit());
        accounts.put(accountId, account);
        return account;
    }

    public Account createCreditAccount(UUID clientId) {
        UUID accountId = UUID.randomUUID();
        Account account = new CreditAccount(accountId, clientId, 0.0, bankSettings.getTransactionsLimit(), bankSettings.getCreditLimit(), bankSettings.getCommission());
        accounts.put(accountId, account);
        return account;
    }

    public IAccount createDepositAccount(UUID clientId) {
        UUID accountId = UUID.randomUUID();
        IAccount account = new DebitAccount(accountId, clientId, 0.0, bankSettings.getAnnualInterest(), bankSettings.getTransactionsLimit());
        accounts.put(accountId, account);
        return account;
    }

    public void addClient(Client client) {
        clients.add(client);
    }
}