import Banks.Accounts.Account;
import Banks.Bank;
import Banks.BankSettings;
import Banks.CentralBank;
import Banks.Clients.Client;
import Banks.Clients.ClientBuilder;
import Exceptions.WithdrawMoneyException;
import Models.CommandType;
import Models.Commands.DepositMoneyCommand;
import Models.Commands.WithdrawMoneyCommand;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class BankTests {
    CentralBank centralBank;
    Bank Tinkoff;
    Bank Sberbank;
    Client MarceloHvostenko;
    Client KarburatorSorokina;
    Account MarcelosDebitAccount;
    Account KarburatorCreditAccount;

    public BankTests() {
        centralBank = CentralBank.getInstance();

        BankSettings SberbankSettings = new BankSettings("Sberbank", 2, 50000.0, 10.0,
                100000.0, 10000.0, 3.0, 5.0, 7.0);
        BankSettings TinkoffSettings = new BankSettings("Tinkoff", 3, 100000.0, 5.0,
                200000.0, 10000.0, 2.0, 5.0, 7.0);

        Sberbank = centralBank.AddBank(SberbankSettings);
        Tinkoff = centralBank.AddBank(TinkoffSettings);
    }

    @Test
    public void DepositMoneyToMarcelosAccountSuccess()
    {
        MarceloHvostenko = new ClientBuilder().withName("Marcelo").withSurname("Hvostenko").withAddress("RF, Saint-P").withPassportNumber(435621).withPassportSeries(1234).Build();

        UUID MarcelosId = MarceloHvostenko.getClientId();
        MarcelosDebitAccount = Sberbank.createDebitAccount(MarcelosId);

        Double moneyAmount = 5000.0;
        var depositMoney = new DepositMoneyCommand(CommandType.DepositMoney, moneyAmount, MarcelosDebitAccount.getAccountId(), MarcelosDebitAccount);
        depositMoney.Execute();

        Double expectedMoneyAmount = 5000.0;
        Double actual = MarcelosDebitAccount.getAccountMoney();
        assertEquals(expectedMoneyAmount, actual);
    }

    @Test
    public void DepositMoneyAndWithdrawMoneyMarcelosAccountSuccess()
    {
        MarceloHvostenko = new ClientBuilder().withName("Marcelo").withSurname("Hvostenko").withAddress("RF, Saint-P").withPassportNumber(435621).withPassportSeries(1234).Build();

        UUID MarcelosId = MarceloHvostenko.getClientId();
        MarcelosDebitAccount = Sberbank.createDebitAccount(MarcelosId);

        Double depositMoneyAmount = 5000.0;
        var depositMoney = new DepositMoneyCommand(CommandType.DepositMoney, depositMoneyAmount, MarcelosDebitAccount.getAccountId(), MarcelosDebitAccount);
        depositMoney.Execute();

        Double withdrawMoneyAmount = 1000.0;
        var withdrawMoney = new WithdrawMoneyCommand(CommandType.WithdrawMoney, withdrawMoneyAmount, MarcelosDebitAccount.getAccountId(), MarcelosDebitAccount);
        withdrawMoney.Execute();

        Double expectedMoneyAmount = 4000.0;
        Double actual = MarcelosDebitAccount.getAccountMoney();
        assertEquals(expectedMoneyAmount, actual);
    }

    @Test
    public void WithdrawTooMuchMoneyFromCreditAccountKarburatorsAccountSendException() {
        KarburatorSorokina = new ClientBuilder().withName("Karbur").withSurname("Sorokina").withAddress("RF, Saint-P").withPassportNumber(424621).withPassportSeries(1234).Build();

        UUID KarbursId = KarburatorSorokina.getClientId();
        KarburatorCreditAccount = Sberbank.createCreditAccount(KarbursId);

        Double withdrawMoneyAmount = 110000.0;
        var withdrawMoney = new WithdrawMoneyCommand(CommandType.WithdrawMoney, withdrawMoneyAmount, KarburatorCreditAccount.getAccountId(), KarburatorCreditAccount);

        var actual = new Exception();
        try {
            withdrawMoney.Execute();
        } catch (Exception exception) {
            actual = exception;
        }

        assertInstanceOf(WithdrawMoneyException.class, actual);
    }

    @Test
    public void GetInterestOnDepositAccountMarcelosAccountSuccess() {
        MarceloHvostenko = new ClientBuilder().withName("Marcelo").withSurname("Hvostenko").withAddress("RF, Saint-P").withPassportNumber(435621).withPassportSeries(1234).Build();
        Sberbank.addClient(MarceloHvostenko);

        UUID MarcelosId = MarceloHvostenko.getClientId();
        MarcelosDebitAccount = Sberbank.createDebitAccount(MarcelosId);

        Double depositMoneyAmount = 51000.0;
        var depositMoney = new DepositMoneyCommand(CommandType.DepositMoney, depositMoneyAmount, MarcelosDebitAccount.getAccountId(), MarcelosDebitAccount);
        depositMoney.Execute();

        centralBank.timeRewind(35);
        Double expectedMoneyAmount = 54060.0;
        Double actual = MarcelosDebitAccount.getAccountMoney();
        assertEquals(expectedMoneyAmount, actual);
    }
}
