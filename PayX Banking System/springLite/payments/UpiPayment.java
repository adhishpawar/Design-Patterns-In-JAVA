package payments;

public class UpiPayment implements PaymentStrategy {

    public UpiPayment() {
        System.out.println("UpiPayment bean created");
    }

    @Override
    public void pay(double amount) {
        System.out.println(" Paying " + amount + " INR using UPI");
    }
}
