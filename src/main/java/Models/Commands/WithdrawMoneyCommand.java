package Models.Commands;

import Banks.Accounts.Account;
import Exceptions.AccountIsNullException;
import Models.CommandType;

import java.util.UUID;

public class WithdrawMoneyCommand extends BankCommand {
    public WithdrawMoneyCommand(CommandType commandType, Double moneyAmount, UUID accountId, Account account) {
        super(commandType, moneyAmount, accountId, account);
    }

    @Override
    public void Execute () {
        if (account != null) {
            account.withdrawMoney(moneyAmount);
            SaveInHistory(this);
        }
        else throw new AccountIsNullException();
    }

    @Override
    public void Undo () {
        if (account != null) {
            account.depositMoney(moneyAmount);
            SaveInHistory(this);
        }
        else throw new AccountIsNullException();
    }
}