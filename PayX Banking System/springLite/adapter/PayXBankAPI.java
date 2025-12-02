package adapter;

public interface PayXBankAPI {
    boolean transfer(double amount, String fromAcc, String toAcc);
}
