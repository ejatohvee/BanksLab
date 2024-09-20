package Models.Commands;

import java.util.Stack;

public class CommandsHistory {
    private Stack<BankCommand> history = new Stack<>();
    public void push(BankCommand command) {
        history.add(command);
    }

    public void pop(BankCommand command) {
        history.getLast();
    }

    public boolean isEmpty() {
        return history.isEmpty();
    }
}