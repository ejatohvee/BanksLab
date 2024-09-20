package Banks.Accounts;

import Exceptions.AccountTermException;
import Exceptions.InterestException;

import java.util.UUID;
import java.time.LocalDateTime;

public class DepositAccount extends Account{
    private final LocalDateTime accountTerm;
    private int minimalInterest;
    private int mediumInterest;
    private int maximumInterest;
    private int currentInterest;
    public DepositAccount(UUID accountId, UUID clientId, Double accountMoney, int interest, Double transactionLimit, LocalDateTime accountTerm, int minimalInterest, int mediumInterest, int maximumInterest) {
        super(accountId, clientId, accountMoney, interest, transactionLimit);
        this.accountTerm = accountTerm;
        this.minimalInterest = minimalInterest;
        this.mediumInterest = mediumInterest;
        this.maximumInterest = maximumInterest;
        if (accountTerm.isBefore(LocalDateTime.now())) {
            throw new AccountTermException("Incorrect deposit account term");
        }
        if (minimalInterest > mediumInterest) {
            throw new InterestException("Minimal interest higher than medium interest");
        }
        if (mediumInterest > maximumInterest) {
            throw new InterestException("Medium interest higher than maximum interest");
        }
    }

    @Override
    public void withdrawMoney(Double moneyAmount) {
        if (LocalDateTime.now().isAfter(accountTerm)) {
            addInterest();
            super.withdrawMoney(moneyAmount);
        }
        else throw new AccountTermException("Account term not ended");
    }
}