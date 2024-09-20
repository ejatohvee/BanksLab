package Banks.Accounts;

import java.util.UUID;

/**
 * Base inetface for all Accounts
 */
public interface IAccount {
    void withdrawMoney(Double moneyAmount);

    void depositMoney(Double moneyAmount);

    /**
     *
     * @param moneyAmount shows how much money we want to transfer
     * @param recipient is the recipient of money
     */
    void transferMoney(Double moneyAmount, IAccount recipient);

    void addInterest();
    void calculateInterest();

    void addUseCommission();

    void updateInterest(int interest);

    void updateTransactionLimit(Double newTransactionLimit);
    UUID getAccountId();
}
