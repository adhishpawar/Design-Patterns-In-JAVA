//This is where everything connects
/*
This is real retry + backoff logic
 Shared state + idempotency
 Callable returns business result
 */
package com.fintech.settlement;

import java.util.concurrent.Callable;

public class PaymentTask extends PaymentService implements Callable<PaymentResult>
{
    private final String txnId;
    private final Account account;
    private final IdempotencyStore store;
    private final int amount;
    private final int maxRetries;

    public PaymentTask(
            String txnId,
            Account account,
            IdempotencyStore store,
            int amount,
            int maxRetries) {

        this.txnId = txnId;
        this.account = account;
        this.store = store;
        this.amount = amount;
        this.maxRetries = maxRetries;
    }

    @Override
    public PaymentResult call() throws Exception
    {
        int attempt = 0;

        while(attempt < maxRetries) {
            attempt++;

            System.out.println(
                    "Thread: " + Thread.currentThread().getName() +
                            " processing " + txnId +
                            " (attempt " + attempt + ")"
            );

            // IDEMPOTENCY CHECK
            if (store.isDuplicate(txnId)) {
                return PaymentResult.duplicate(txnId);
            }

            validate();

            if(!fraudCheck())
            {
                backoff(attempt);
                continue;
            }

            boolean debited = account.debit(amount);

            if(!debited)
                return PaymentResult.failed(txnId, "INSUFFICIENT_BALANCE");

            return PaymentResult.success(txnId);
        }

        return PaymentResult.failed(txnId, "RETRIES_EXHAUSTED");
    }

    private void backoff(int attempt) throws InterruptedException
    {
        long delay = (long) Math.pow(2, attempt) * 100;
        Thread.sleep(delay);
    }
}