package Banks.Accounts;

import Exceptions.WithdrawMoneyException;

import java.util.UUID;

public class DebitAccount extends Account {
    public DebitAccount(UUID accountId, UUID clientId, Double accountMoney, int interest, Double transactionLimit) {
        super(accountId, clientId, accountMoney, interest, transactionLimit);
    }

    @Override
    public void withdrawMoney(Double moneyAmount) {
        if (accountMoney - moneyAmount < 0) {
            throw new WithdrawMoneyException();
        }
        super.withdrawMoney(moneyAmount);
        addInterest();
    }
}