package Banks.Accounts;

import Exceptions.InterestException;
import Exceptions.TransactionException;
import Exceptions.WithdrawMoneyException;

import java.util.UUID;

/**
 * Account is a base abstract class for other accounts, which implements interface IAccount with all base
 * account operations
 */
public abstract class Account implements IAccount {
    protected UUID accountId;
    protected UUID clientId;
    protected Double accountMoney;
    protected int interest;
    protected Double interestMoney = 0.0;
    protected int useCommission;
    protected Double transactionLimit;

    public Account(UUID accountId, UUID clientId, Double accountMoney, int interest, Double transactionLimit) {
        this.accountId = accountId;
        this.clientId = clientId;
        this.accountMoney = accountMoney;
        this.interest = interest;
        this.transactionLimit = transactionLimit;
    }

    /**
     *
     * @param moneyAmount is amount of money we want to withdraw
     */
    @Override
    public void withdrawMoney(Double moneyAmount) {
        if (moneyAmount < 0) {
            throw new WithdrawMoneyException("Cant withdraw negative amount of money");
        }
        accountMoney -= moneyAmount;
    }

    @Override
    public void depositMoney(Double moneyAmount) {
        accountMoney += moneyAmount;
    }

    @Override
    public void transferMoney(Double moneyAmount, IAccount recipient) {
        if (moneyAmount < 0) {
            throw new WithdrawMoneyException("Cant withdraw negative amount of money");
        }
        withdrawMoney(moneyAmount);
        recipient.depositMoney(moneyAmount);
    }

    @Override
    public void calculateInterest() {
        interestMoney += accountMoney * (interest) / 1000;
    }

    @Override
    public void addInterest() {
        depositMoney(interestMoney);
    }
    @Override
    public void addUseCommission() {
        if (accountMoney < 0) {
            withdrawMoney((accountMoney * useCommission) / 100);
        }
    }

    public void updateInterest(int newInterest) {
        if (interest < 0) {
            throw new InterestException("Interest cant be less than null");
        }
        this.interest = newInterest;
    }

    public void updateTransactionLimit(Double newTransactionLimit) {
        if (newTransactionLimit < 0) {
            throw new TransactionException("Transaction limit cant be less than null");
        }
        this.transactionLimit = newTransactionLimit;
    }

    public UUID getAccountId() {
        return accountId;
    }
    public Double getAccountMoney() {
        return accountMoney;
    }

}