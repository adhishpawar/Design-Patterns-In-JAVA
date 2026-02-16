package com.fintech.settlement;

public abstract class PaymentService {

    protected void validate() {
        System.out.println("Validating the Payment");
    }

    protected boolean fraudCheck() {
        // Simulate random fraud system latency/failure
        return Math.random() > 0.1;
    }
}
