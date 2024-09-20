package Models.Commands;

import Banks.Accounts.Account;
import Exceptions.AccountIsNullException;
import Models.CommandType;

import java.util.UUID;

public class DepositMoneyCommand extends BankCommand {
    public DepositMoneyCommand(CommandType commandType, Double moneyAmount, UUID accountId, Account account) {
        super(commandType, moneyAmount, accountId, account);
    }

    public void Execute() {
        if (account != null) {
            account.depositMoney(moneyAmount);
            SaveInHistory(this);
        }
        else throw new AccountIsNullException();
    }

    public void Undo() {
        if (account != null) {
            account.withdrawMoney(moneyAmount);
            SaveInHistory(this);
        }
        else throw new AccountIsNullException();
    }
}
