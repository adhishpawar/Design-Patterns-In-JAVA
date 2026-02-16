/*
Has NO threading
Pure business logic
Testable independently
Base class
*/


public abstract class PaymentService
{
    protected void validate()
    {
        System.out.println("Validating the Payment");
    }

    protected boolean fruadCheck()
    {
        System.out.println("Fraud check passed");
        return  true;
    }

    protected void debit()
    {
        System.out.println("Amount debited");
    }
}