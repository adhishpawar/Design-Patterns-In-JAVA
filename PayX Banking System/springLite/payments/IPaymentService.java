package payments;

public interface IPaymentService {
    void pay(String accId, double amount, String method);
}
