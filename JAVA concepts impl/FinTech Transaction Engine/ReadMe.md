# 🏦 Java Concurrency in Action – FinTech Transaction Engine

## 📌 Overview

This project demonstrates **real-world Java concurrency** by building a **high-throughput FinTech transaction engine**.  
It goes beyond basics and shows **how concurrency bugs occur in production** and **how to fix them correctly**.

The focus is on:

- Safe multithreading

- Deadlock reproduction

- Deadlock prevention using proven concurrency patterns


---

## 🎯 Why This Project

In FinTech systems (payments, banking, trading):

- Thousands of transactions run **concurrently**

- Money must **never be lost**

- Systems must **never freeze**


This project simulates those exact challenges using **core Java concurrency APIs**.

---

## 🧠 Concurrency Concepts Covered

### Core Java Concurrency

- Thread safety

- Race conditions

- Shared mutable state


### `java.util.concurrent`

- `AtomicLong` for lock-free balance updates

- `ConcurrentHashMap` for account storage

- `ReentrantLock` for fine-grained locking

- `ExecutorService` for high throughput

- `CountDownLatch` for synchronization


---

## 🏗️ What We Built

### 1️⃣ Thread-Safe Account Model

Each account has:

- Atomic balance

- Explicit lock for multi-account operations


`class Account {     long id;     AtomicLong balance;     ReentrantLock lock; }`

---

### 2️⃣ Concurrent Account Store

Accounts are stored safely using `ConcurrentHashMap`.

`ConcurrentHashMap<Long, Account>`

This allows:

- Concurrent reads

- Thread-safe creation

- High scalability


---

## 💥 Deadlock Demonstration (Intentional Bug)

### Problem

Two threads perform transfers in opposite directions:

- Thread-1: A → B

- Thread-2: B → A


### Buggy Locking

`from.lock.lock(); to.lock.lock();`

### Result

- Thread-1 locks A, waits for B

- Thread-2 locks B, waits for A

- **System freezes forever**


This demonstrates a **real Java deadlock** exactly as seen in production systems.

---

## 🔍 How Deadlock Was Verified

- Application hangs

- JVM remains alive

- `jstack` shows:

  `Found one Java-level deadlock`


---

## ✅ Deadlock Prevention (Production Fix)

### Solution: **Lock Ordering**

Always acquire locks in a globally consistent order.

`Account first = from.id < to.id ? from : to; Account second = from.id < to.id ? to : from;`

### Why It Works

- Circular wait is eliminated

- Deadlocks become **impossible by design**


This is a **standard FinTech concurrency pattern**.

---

## ⚡ High-Throughput Testing

- Multiple threads

- Parallel transfers

- Coordinated using `CountDownLatch`


`ExecutorService pool = Executors.newFixedThreadPool(20);`

Result:

- No deadlocks

- No incorrect balances

- Safe under heavy load


---

## 🧪 What This Proves

✔ Thread-safe money movement  
✔ Deadlock reproduction and diagnosis  
✔ Deadlock-free design  
✔ Real concurrency correctness  
✔ FinTech-ready system behavior