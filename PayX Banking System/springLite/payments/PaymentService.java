package payments;

import core.BeanFactory;

public class PaymentService {

    public void pay(double amount, String method) {

        System.out.println("processing payment of "+ amount + "via" + method);



        // 2. Notify Email
        sendEmail(amount, method);

        // 3. Notify SMS
        sendSms(amount, method);

        // 4. Update ledger
        updateLedger(amount, method);

        // 5. Notify fraud monitoring
        sendToFraudSystem(amount, method);

        // 6. Push app notification
        sendPushNotification(amount, method);
    }

    private void sendEmail(double amount, String method) {
        System.out.println(" Email: Payment of " + amount + " via " + method);
    }

    private void sendSms(double amount, String method) {
        System.out.println(" SMS: Payment of " + amount + " via " + method);
    }

    private void updateLedger(double amount, String method) {
        System.out.println(" Ledger updated for payment " + amount + " via " + method);
    }

    private void sendToFraudSystem(double amount, String method) {
        System.out.println(" Fraud system notified for payment " + amount + " via " + method);
    }

    private void sendPushNotification(double amount, String method) {
        System.out.println(" App notification: Payment " + amount + " via " + method);
    }
}

//        Tight coupling