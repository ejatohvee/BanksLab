package Models.Commands;

import Banks.Accounts.Account;
import Exceptions.AccountIsNullException;
import Models.CommandType;

import java.util.UUID;

public class TransferMoneyCommand extends BankCommand {
    protected Account recipientsAccount;
    public TransferMoneyCommand(CommandType commandType, Double moneyAmount, UUID accountId, Account account, Account recipientsAccount) {
        super(commandType, moneyAmount, accountId, account);
        this.recipientsAccount = recipientsAccount;
    }

    @Override
    public void Execute() {
        if (account != null && recipientsAccount != null) {
            account.transferMoney(moneyAmount, recipientsAccount);
        }
        else if (account == null) {
            throw new AccountIsNullException();
        }
        else throw new AccountIsNullException("Recipients account is null");
    }

    @Override
    public void Undo() {
        if (account != null && recipientsAccount != null) {
            account.depositMoney(moneyAmount);
            recipientsAccount.withdrawMoney(moneyAmount);
        }
        else if (account == null) {
            throw new AccountIsNullException();
        }
        else throw new AccountIsNullException("Recipients account is null");
    }
}
