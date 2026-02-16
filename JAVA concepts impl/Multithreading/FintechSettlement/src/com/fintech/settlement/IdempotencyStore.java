/*
Prevents:
retries double-debiting
duplicate API calls
network retries issues
*/

package com.fintech.settlement;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class IdempotencyStore
{
    private final Set<String> processedTransactions = ConcurrentHashMap.newKeySet();

    public boolean isDuplicate(String txnId) {
        return !processedTransactions.add(txnId);
    }
}