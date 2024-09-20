package Models.Commands;

import Banks.Accounts.Account;
import Models.CommandType;

import java.util.UUID;

public abstract class BankCommand {
    protected CommandsHistory history = new CommandsHistory();
    protected CommandType commandType;
    protected Double moneyAmount;
    protected UUID accountId;
    protected Account account;

    public BankCommand(CommandType commandType, Double moneyAmount, UUID accountId, Account account) {
        this.commandType = commandType;
        this.moneyAmount = moneyAmount;
        this.accountId = accountId;
        this.account = account;
    }

    public abstract void Execute();
    public abstract void Undo();
    void SaveInHistory(BankCommand command) {
        history.push(command);
    }
}