/*
✔ Industry pattern
✔ No exception-driven logic
✔ Clear outcome
*/

package com.fintech.settlement;

public class PaymentResult {

    public enum Status {
        SUCCESS, FAILED, DUPLICATE
    }

    public final String txnId;
    public final Status status;
    public final String reason;

    private PaymentResult(String txnId, Status status, String reason) {
        this.txnId = txnId;
        this.status = status;
        this.reason = reason;
    }

    public static PaymentResult success(String txnId) {
        return new PaymentResult(txnId, Status.SUCCESS, null);
    }

    public static PaymentResult failed(String txnId, String reason) {
        return new PaymentResult(txnId, Status.FAILED, reason);
    }

    public static PaymentResult duplicate(String txnId) {
        return new PaymentResult(txnId, Status.DUPLICATE, "DUPLICATE_REQUEST");
    }
}
