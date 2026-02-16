/*
run() does NOT contain logic
It only delegates
Business logic is reusable
*/

public class PaymentTask extends PaymentService implements Runnable
{
    private final String paymentId;

    public PaymentTask(String paymentId)
    {
        this.paymentId = paymentId;
    }

    // CORE BUSINESS FLOW --> Thread independent
    public void processPayment()
    {
        validate();

        if(!fruadCheck())
        {
            System.out.println("Fraud Detected for " + paymentId);
            return;
        }

        debit();
        System.out.println("Payment completed" + paymentId);
    }

    // THREAD ENTRY POINT
    @Override
    public void run()
    {
        System.out.println(
                "Thread: " + Thread.currentThread().getName() +
                        " processing " + paymentId
        );
        processPayment();
    }
}