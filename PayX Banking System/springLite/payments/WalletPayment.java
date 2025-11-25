package payments;

public class WalletPayment implements PaymentStrategy {

    public WalletPayment() {
        System.out.println(" WalletPayment bean created");
    }

    @Override
    public void pay(double amount) {
        System.out.println(" Paying " + amount + " INR using Wallet");
    }
}
