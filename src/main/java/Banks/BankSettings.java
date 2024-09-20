package Banks;

import Exceptions.CreateBankException;

public class BankSettings {
    public String name;
    private final int annualInterest;
    private final Double creditLimit;
    private final Double commission;
    private final Double transactionsLimit;
    private final Double suspiciousAccountTransactionLimit;
    private Double belowFiftyThousandPercent;
    private Double betweenFiftyAndHundredThousandPercent;
    private Double aboveHundredThousandPercent;

    public BankSettings(String name, int annualInterest, Double creditLimit, Double commission, Double transactionsLimit, Double suspiciousAccountTransactionLimit, Double belowFiftyThousandPercent, Double betweenFiftyAndHundredThousandPercent, Double aboveHundredThousandPercent) {
        if (belowFiftyThousandPercent > betweenFiftyAndHundredThousandPercent)
            throw new CreateBankException("Below 50000 percent cant less then 50000-100000 percent");
        if (betweenFiftyAndHundredThousandPercent > aboveHundredThousandPercent)
            throw new CreateBankException("Between 50000-100000 percent cant less then 100000+ percent");
        if (commission < 0)
            throw new CreateBankException("Commission cant be less then 0");

        this.name = name;
        this.annualInterest = annualInterest;
        this.creditLimit = creditLimit;
        this.commission = commission;
        this.transactionsLimit = transactionsLimit;
        this.suspiciousAccountTransactionLimit = suspiciousAccountTransactionLimit;
        this.belowFiftyThousandPercent = belowFiftyThousandPercent;
        this.betweenFiftyAndHundredThousandPercent = betweenFiftyAndHundredThousandPercent;
        this.aboveHundredThousandPercent = aboveHundredThousandPercent;
    }

    public int getAnnualInterest() {
        return annualInterest;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCreditLimit() {
        return creditLimit;
    }

    public Double getCommission() {
        return commission;
    }

    public Double getTransactionsLimit() {
        return transactionsLimit;
    }

    public Double getSuspiciousAccountTransactionLimit() {
        return suspiciousAccountTransactionLimit;
    }
}