package Banks;

import Banks.Accounts.Account;
import Exceptions.CreateBankException;
import Exceptions.TimeException;

import java.util.ArrayList;
import java.util.List;

public class CentralBank {
    private static CentralBank instance;
    private final List<Bank> banks;
    private Double bankToBankComission = 0.05;

    public CentralBank(List<Bank> banks) {
        this.banks = banks;
    }

    public static CentralBank getInstance() {
        if (instance == null) instance = new CentralBank(new ArrayList<>());
        return instance;
    }

    public Bank AddBank(BankSettings bankSettings) {
        Bank newBank = new Bank(bankSettings);
        if (banks.contains(newBank)) {
            throw new CreateBankException("Such bank is already exists");
        }
        banks.add(newBank);
        return newBank;
    }

    public void BankToBankMoneyTransfer(Account firstAccount, Double moneyAmount, Account secondAccount) {
        firstAccount.withdrawMoney(moneyAmount);
        secondAccount.depositMoney(moneyAmount - moneyAmount * bankToBankComission);
    }

    public void calculateBanksInterests() {
        for (Bank bank : banks) {
            bank.calculateInterests();
        }
    }

    public void timeRewind(int days) {
        if (days < 0) {
            throw new TimeException("Days amount can't be less than 0");
        }
        for (int i = 1; i <= days; i++) {
            calculateBanksInterests();
            for (Bank bank : banks) {
                if (i % 30 == 0) {
                    bank.chargeInterest();
                    bank.chargeCommission();
                }
            }
        }
    }
}