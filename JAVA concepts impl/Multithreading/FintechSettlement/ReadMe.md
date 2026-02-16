
# 🚀 High-Load Payment Settlement System

**Java Concurrency | Callable | ExecutorService | Idempotency | Retry & Locking**

---

## 📌 Overview

This project implements a **high-load payment settlement engine** designed to simulate **real-world FinTech payment processing** under heavy concurrency.

The system safely processes **thousands of concurrent payment requests**, ensuring:

- **Exactly-once debit semantics**

- **Thread-safe balance updates**

- **Duplicate request protection**

- **Retry handling for transient failures**

- **Deterministic final account state**


This is **production-style Java concurrency**, not a toy example.

---

## 🎯 Problem Statement

In FinTech systems (UPI, cards, wallets, trading platforms):

- Thousands of payment requests arrive concurrently

- Requests may be **duplicated** due to retries or network failures

- Fraud systems may fail transiently

- Multiple threads may attempt to update the same account

- **Money must never be double-debited**


The goal is to build a system that:

- Scales under load

- Remains correct under concurrency

- Guarantees data consistency


---

## 🧠 System Design

### High-Level Architecture

```
Incoming Payment Requests
        ↓
Callable Payment Tasks
        ↓
ExecutorService (Thread Pool)
        ↓
Retry + Backoff Logic
        ↓
Idempotency Check
        ↓
Account Locking
        ↓
Ledger Update
        ↓
Result Collection
```

---

## 🔧 Key Technologies & Concepts Used

- **Callable** – returns execution result per task

- **ExecutorService** – controlled concurrency with thread pooling

- **Future** – result handling & synchronization

- **ReentrantLock** – thread-safe shared balance updates

- **ConcurrentHashMap** – idempotency enforcement

- **Retry with exponential backoff** – resilience

- **High-load simulation** – stress testing correctness


---

## 📦 Package Structure

```
com.fintech.settlement
│
├── Account.java
├── IdempotencyStore.java
├── PaymentService.java
├── PaymentTask.java
├── PaymentResult.java
└── PaymentSimulation.java
```

---

## 🧩 Component Breakdown

---

### 1️⃣ Account (Shared State with Locking)

**Purpose**

- Represents a bank/wallet account

- Protects balance against race conditions


**Key Concepts**

- Shared mutable state

- Explicit locking (`ReentrantLock`)

- Prevents double spending


```java
lock.lock();
try {
    if (balance >= amount) {
        balance -= amount;
        return true;
    }
} finally {
    lock.unlock();
}
```

---

### 2️⃣ IdempotencyStore (Exactly-Once Guarantee)

**Purpose**

- Ensures the same transaction ID is processed only once

- Blocks duplicate API calls and retries


**Why This Matters**

- Payment gateways must be idempotent

- Network retries should not debit money again


Implemented using:

```java
ConcurrentHashMap.newKeySet()
```

---

### 3️⃣ PaymentService (Business Logic Layer)

**Purpose**

- Holds business rules (validation, fraud check)

- Contains **no threading logic**


**Design Principle**

> Separate business logic from execution model

This allows:

- Unit testing without threads

- Reuse across sync/async flows


---

### 4️⃣ PaymentTask (Callable Task)

**Purpose**

- Represents a single payment request

- Implements `Callable<PaymentResult>`


**Responsibilities**

- Execute payment flow

- Apply retries with backoff

- Enforce idempotency

- Return structured result


**Why Callable (not Runnable)?**

- Returns result

- Propagates failures cleanly

- Enables retry & reporting


---

### 5️⃣ Retry & Exponential Backoff

**Why Needed**

- Fraud systems and downstream services fail transiently

- Immediate failure is not acceptable


**Strategy**

- Retry up to N times

- Delay increases exponentially

- Fail gracefully if retries exhausted


```java
long delay = (long) Math.pow(2, attempt) * 100;
Thread.sleep(delay);
```

---

### 6️⃣ ExecutorService (Concurrency Control)

**Purpose**

- Manages a fixed number of worker threads

- Prevents thread explosion

- Provides predictable performance


```java
ExecutorService executor =
    Executors.newFixedThreadPool(20);
```

Tasks are:

- Submitted

- Queued

- Executed by reusable worker threads


---

### 7️⃣ High-Load Simulation

**What We Simulate**

- 1000 concurrent payment requests

- Duplicate transaction IDs

- Shared account balance

- Random fraud failures


**Why**

- To validate correctness under stress

- To observe behavior under contention


---

## 📊 Sample Output Analysis

```
SUCCESS   : 799
FAILED    : 0
DUPLICATE : 201
BALANCE   : 40200
```

### Interpretation

- **799 successful payments**

- **201 blocked duplicate requests**

- **No failures**

- **Final balance matches expected ledger math**


This proves:

- Locking worked

- Idempotency worked

- Retry logic recovered transient failures

- No race conditions occurred


---

## 🔐 Correctness Guarantees

|Guarantee|How It’s Achieved|
|---|---|
|No double debit|Idempotency store|
|Thread safety|ReentrantLock|
|Scalability|ExecutorService|
|Failure recovery|Retry + backoff|
|Deterministic state|Callable + Future|

---

## 💼 Real-World Applicability

This design mirrors systems used in:

- Payment gateways (UPI, cards)

- Wallet services

- Trading settlement engines

- Ledger processors

- Batch settlement jobs


---

## 🚀 Possible Extensions

- Account-to-account transfers (deadlock-free)

- Timeouts & task cancellation

- Custom ThreadPoolExecutor tuning

- Metrics (TPS, latency percentiles)

- Compensation flows (refunds, reversals)


---

## ✅ Summary

This project demonstrates:

- Deep understanding of Java concurrency

- Real FinTech system design thinking

- Correctness under load

- Clean separation of concerns

- Production-ready patterns


This is **portfolio-grade Java**, not tutorial code.

---
