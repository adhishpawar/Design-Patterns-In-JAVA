package payxCore.credit.model;

import java.time.LocalDate;
import java.util.Objects;

public final class Transaction {

    private final LocalDate date;
    private final double amount;
    private final TransactionType type; //CREDIT or DEBIT

    public Transaction(LocalDate date, double amount, TransactionType type)
    {
        this.date = Objects.requireNonNull(date);
        this.amount = amount;
        this.type = Objects.requireNonNull(type);
    }

    public LocalDate getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }
}
