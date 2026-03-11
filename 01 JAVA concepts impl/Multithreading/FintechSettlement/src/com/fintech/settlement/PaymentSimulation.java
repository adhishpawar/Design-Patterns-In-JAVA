package com.fintech.settlement;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class PaymentSimulation {

    public static void main(String[] args) throws Exception {

        int totalTransactions = 1000;
        int threadPoolSize = 20;

        Account account = new Account(200_000);
        IdempotencyStore store = new IdempotencyStore();

        ExecutorService executor =
                Executors.newFixedThreadPool(threadPoolSize);

        List<Future<PaymentResult>> futures = new ArrayList<>();

        for (int i = 0; i < totalTransactions; i++) {

            String txnId = "TXN-" + (i % 900); // simulate duplicates

            PaymentTask task = new PaymentTask(
                    txnId,
                    account,
                    store,
                    200,
                    3
            );

            futures.add(executor.submit(task));
        }

        int success = 0, failed = 0, duplicate = 0;

        for (Future<PaymentResult> future : futures) {
            PaymentResult result = future.get();
            switch (result.status) {
                case SUCCESS -> success++;
                case FAILED -> failed++;
                case DUPLICATE -> duplicate++;
            }
        }

        executor.shutdown();

        System.out.println("\n==== FINAL REPORT ====");
        System.out.println("SUCCESS   : " + success);
        System.out.println("FAILED    : " + failed);
        System.out.println("DUPLICATE : " + duplicate);
        System.out.println("BALANCE   : " + account.getBalance());
    }
}
