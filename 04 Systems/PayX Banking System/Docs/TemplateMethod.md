Template Method is a **behavioral design pattern** that defines the **skeleton of an algorithm** in a base class, and allows subclasses to override specific steps of the algorithm _without changing its structure_.

- A parent class defines the sequence of steps of a process, and child classes customize certain steps.

# What Problem are we solving?

Imagine in PayX we have multiple payment types:

- UPI
- Card
- Wallet
- NetBanking
- EMI
- Standing instruction

All require the SAME _overall_ processing steps:
```
processPayment:
    validate customer
    verify balance
    block amount
    debit
    record transaction
    notify user

```

## ❌ WITHOUT Template Method

Every implementation rewrites everything:
```
class UpiPayment {
    validateCustomer();
    validateBalance();
    blockAmount();
    debit();
    recordTransaction();
    notify();
}

class CardPayment {
    validateCustomer();
    validateBalance();
    blockAmount();
    debit();
    recordTransaction();
    notify();
}

```

Problems:
- duplicated code
- inconsistent steps between payment types
- one developer may forget logging or auditing
- security & compliance broken
- chaotic business process
---

# ✔ WITH Template Method

We extract the algorithm:
```
abstract class PaymentTemplate {
    final void processPayment() {
        validateCustomer();
        validateBalance();
        debit();
        recordTransaction();
        notify();
    }
}
```

Each child defines ONLY what differs:
```
class UpiPayment extends PaymentTemplate {
    validateCustomer() { ... }
    debit() { ... }
}
```

# 🎯 REAL PROBLEM SOLVED

### 🛑 Problem:

Payments lack consistency & compliance.

Different developers write:

- different validation logic
- different debit logic
- different ledger logic
- different notification logic

No guarantee of:

✔ standard logging  
✔ AML compliance  
✔ auditing  
✔ KYC validation  
✔ fraud handling

---

### 💡 Solution (Template Method):

Define the standard required workflow (cannot be changed),  
but allow customization where needed.

---

# 🔥 PayX Real Use Case

Our required payment blueprint is:

`validateCustomer() validateBalance() debitAccount() recordTransaction() sendNotification()`

And **this sequence must not change**.

We must legally verify:

- KYC status
- FATCA compliance
- AML checks
- RBI transaction guidelines
- Anti-fraud decision tree

So base class enforces policy.

---

# 👨‍⚖️ COMPLIANCE PERSPECTIVE

Regulatory banking systems require:

- consistent processing
- mandatory logging
- easy auditability
- predictable flow
- unmodifiable core steps

Template Method enforces this.

---

# 🏛️ Relation to Spring Framework / Spring Boot

Spring uses Template Method HEAVILY.

---

## ✔ Example: `JdbcTemplate`

Instead of manually:

- opening DB connection
- preparing statement
- executing query
- handling result set
- cleaning resources

You only write:

`jdbcTemplate.query(sql, rowMapper);`

Spring controls:

1. connection management
2. transaction context
3. SQL execution
4. error handling
5. resource cleanup

You implement only:

`RowMapper`

---

## ✔ Example: `RestTemplate`

Instead of writing:

- open HTTP connection
- set headers
- handle errors
- parse JSON

You just:

`restTemplate.getForObject(url, Response.class)`

Spring hides ALL complexity.

---

## ✔ Inside Spring Framework itself

This pattern is used in:

- `AbstractController`
- `AbstractView`
- `AbstractApplicationContext`
- `AbstractMessageHandler`
- `AbstractRoutingDataSource`
- `HandlerInterceptorAdapter`

Example:
```
public final void renderMergedOutputModel(...) {
    checkModel();
    prepareResponse();
    renderOutput();
    flushBuffer();
}
```

Some steps are final, some customizable.

---

# 🔥 Key Spring Principle enabled by Template Method:

### Developers extend behavior

WITHOUT modifying core framework code.

This gives Spring:

✔ stability  
✔ flexibility  
✔ extensibility  
✔ reliability

---

# 🧩 Where Template Method fits in our PayX architecture

We already have:

✔ Singleton — resource consistency  
✔ Factory — bean creation  
✔ Strategy — payment selection  
✔ Observer — notifications  
✔ Chain of Responsibility — validations pipeline

Template Method sits here:

```
[ Validation (CoR) ] 
       ↓
[ PaymentTemplate ]   <-- Mandatory steps
       ↓
[ Strategy (UPI/Card) ]
       ↓
[ Observer notifications ]

```