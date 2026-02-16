# High-Frequency Transaction Ledger (Multithreading Deep Dive)

## 📌 Overview

This project simulates a **high-frequency financial transaction engine** where multiple threads concurrently update a shared ledger balance.  
It demonstrates **real-world multithreading problems** such as race conditions, lost updates, memory visibility issues, and shows **progressive solutions** from naïve to lock-free designs.

This is the kind of problem that **breaks banking systems, trading platforms, wallets, and payment gateways** if implemented incorrectly.

---

## 🧠 Problem Statement

Multiple threads concurrently perform financial operations on a shared ledger:

- **Credit**
- **Debit**
- **Audit logging**

### Constraints:
- One shared balance
- 100+ concurrent threads
- Millions of operations
- Final balance **must be correct**
- **No lost updates**
- **No inconsistent reads**
- **High throughput required**

---

## ⚠️ Why This Is Hard

Financial operations are **not atomic by default** in Java.

``` java
balance += amount;
```

This expands internally to:

1. Read balance

2. Modify value

3. Write back


When threads interleave, **race conditions occur**.

## 🧪 Architecture Evolution

This project intentionally evolves through **multiple implementations** to expose and fix concurrency issues.
```
Naïve → Synchronized → Explicit Locks → Lock-Free (CAS)

```

Each step highlights **trade-offs between correctness and performance**.

## 🧨 Stage 1: Naïve (Broken) Implementation

### Characteristics

- No synchronization

- Fast

- Incorrect under concurrency


### Failure Modes

- Lost updates

- Non-deterministic results

- Appears correct in low contention (dangerous!)

### Outcome

❌ Final balance varies across runs  
❌ Race conditions guaranteed

---

## 🔒 Stage 2: Synchronized Ledger

### Characteristics

- Method-level synchronization

- Correct

- Poor scalability


### Outcome

✅ Correct results  
❌ Threads block each other  
❌ Throughput degrades under load

---

## ⚔️ Stage 3: Explicit Locking (ReentrantLock)

### Characteristics

- Fine-grained control

- Better than synchronized

- Still blocking


### Outcome

✅ Correct  
✅ More flexible than synchronized  
❌ Still suffers under extreme contention

---

## 🚀 Stage 4: Lock-Free Ledger (Atomic / CAS)

### Core Idea

Avoid locks entirely using **Compare-And-Swap (CAS)** at the CPU level.

### Characteristics

- Lock-free

- Non-blocking

- Highly scalable

- Production-grade


### Outcome

✅ Always correct  
✅ High throughput  
✅ Minimal contention  
✅ Preferred for high-frequency systems

---

## 🧾 Audit Logging Considerations

Audit logging is **intentionally decoupled** from balance updates.

### Why?

- Logging is I/O bound

- Blocking ledger updates for logging kills performance


### Recommended Approach

- Asynchronous logging

- Separate executor / queue

- Event-driven audit pipeline