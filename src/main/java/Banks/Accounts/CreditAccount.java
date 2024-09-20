package Banks.Accounts;

import Exceptions.WithdrawMoneyException;

import java.util.UUID;

public class CreditAccount extends Account {

    private final Double creditLimit;
    private Double useCommission;

    public CreditAccount(UUID accountId, UUID clientId, Double accountMoney, Double transactionLimit, double creditLimit, double useCommission) {
        super(accountId, clientId, accountMoney, 0, transactionLimit);
        this.creditLimit = creditLimit;
        this.useCommission = useCommission;
    }

    @Override
    public void withdrawMoney(Double moneyAmount) {
        if (accountMoney - moneyAmount < creditLimit) {
            throw new WithdrawMoneyException("Withdrawn money amount exceeds credit limit");
        }
        super.withdrawMoney(moneyAmount);
        super.addUseCommission();
    }
}
