package payments;

public class CardPayment implements PaymentStrategy {

    public CardPayment() {
        System.out.println(" CardPayment bean created");
    }

    @Override
    public void pay(double amount) {
        System.out.println(" Paying " + amount + " INR using Card");
    }
}
