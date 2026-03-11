# SOLID Principles — LLD Notes

> Notes focused on concept clarity and interview readiness.  
> No code — pure understanding, mental models, and real-world analogies.

---

## What is SOLID?

SOLID is a set of 5 design principles that make software systems:

- **Maintainable** — easy to change without breaking things
- **Extensible** — easy to add new features
- **Testable** — easy to write unit tests
- **Readable** — easy for others to understand

These principles were introduced by **Robert C. Martin (Uncle Bob)** and are the foundation of every serious LLD interview.

---

## The 5 Principles at a Glance

| Letter | Principle | Core Idea |
|--------|-----------|-----------|
| **S** | Single Responsibility | One class → one reason to change |
| **O** | Open Closed | Open to extend, closed to modify |
| **L** | Liskov Substitution | Subclass must not break parent's contract |
| **I** | Interface Segregation | Don't force unused methods on a class |
| **D** | Dependency Inversion | Depend on abstractions, not concretions |

---

## S — Single Responsibility Principle (SRP)

### The Idea

> A class should have **one and only one reason to change.**

Each class should own exactly one job. If a class is doing multiple things, it has multiple reasons to change — and that makes it fragile.

### Real World Analogy

Think of a restaurant:
- The **chef** only cooks
- The **waiter** only serves
- The **cashier** only handles billing

No one does everything. Each person has one clear responsibility.

### Where it Breaks

Imagine a single `UserService` class that:
- Validates user input
- Saves to database
- Sends a welcome email
- Generates a report

If you change the email template, you touch the same class that handles database logic. That's risky and messy.

### The Fix

Split it:
- `UserService` → business logic only
- `UserRepository` → database only
- `EmailService` → notifications only

Now each class has exactly one reason to change.

### Interview Signal

When an interviewer sees a class doing too many things, they expect you to immediately say:  
*"This violates SRP. I'll split this into separate classes based on responsibility."*

---

## O — Open Closed Principle (OCP)

### The Idea

> Software should be **open for extension** but **closed for modification.**

You should be able to add new behavior without touching existing, tested code.

### Real World Analogy

Think of a **power strip** (extension board). You can plug in a new device without rewiring the wall socket. The wall socket (existing system) doesn't change — you just extend from it.

### Where it Breaks

A payment system with:

```
if type == "CREDIT_CARD" → handle
if type == "PAYPAL" → handle
```

Every time you add a new payment method (UPI, Crypto), you modify the existing class. This is risky — you might accidentally break CreditCard logic while adding UPI.

### The Fix

Create a `PaymentMethod` interface. Each payment type implements it separately. The `PaymentProcessor` never needs to change — it just calls the interface method.

Adding UPI? Create a `UPIPayment` class. Done. No existing code touched.

### Interview Signal

Whenever you see `if-else` chains or `switch` statements based on type, immediately say:  
*"This is an OCP violation. I'll extract an interface and use polymorphism."*

---

## L — Liskov Substitution Principle (LSP)

### The Idea

> Objects of a subclass should be **replaceable** with objects of the parent class without breaking the program.

If you swap a child object in place of the parent, the program should still behave correctly.

### Real World Analogy

If you have a `Bird` class with a `fly()` method, and `Sparrow` extends `Bird` — that works fine. But if `Penguin` extends `Bird` and `fly()` throws an error or does nothing — that breaks LSP. A penguin cannot substitute a bird in a flying context.

### The Vehicle Problem (From Your Notes)

`Vehicle` has `hasEngine()`.  
`Car` and `Motorcycle` → have engines → return `true`. ✅  
`Bicycle` → no engine → returns `null` → **NullPointerException** at runtime. ❌

`Bicycle` cannot substitute `Vehicle` properly. It reduces the capability of the parent. That's an LSP violation.

### Root Cause

Bad abstraction. The assumption that *all vehicles have engines* is wrong.

### The Fix

- `Vehicle` → only keeps `getNumberOfWheels()` (common to all)
- `EngineVehicle` → a separate interface with `hasEngine()`
- `Car`, `Motorcycle` → implement both
- `Bicycle` → only implements `Vehicle`

Now every substitution is correct. No broken contracts.

### The Classic Rectangle/Square Problem

`Square extends Rectangle` seems logical geometrically. But if `Rectangle` lets you set width and height independently, a `Square` breaks this — it forces width = height. Swapping `Square` where `Rectangle` is expected gives wrong area results.

Fix: Give both their own independent implementations under a common `Shape` interface.

### Interview Signal

LSP violations usually show up as:
- `UnsupportedOperationException`
- `NullPointerException` from child class methods
- Overridden methods that do nothing or throw errors

Say: *"The child class is reducing the parent's capabilities. This violates LSP. I'll restructure the inheritance hierarchy."*

---

## I — Interface Segregation Principle (ISP)

### The Idea

> Clients should **not be forced to depend on methods they don't use.**

A fat interface that forces every implementor to define methods irrelevant to them is a design smell.

### Real World Analogy

Imagine a job description that says:  
*"Must code, design UI, manage the database, write marketing copy, and do customer support."*

A backend developer forced to implement a `writeMarketingCopy()` method they'll never use — that's ISP violation in real life.

### Where it Breaks

A `Worker` interface with both `work()` and `eat()`:
- `HumanWorker` → implements both ✅
- `RobotWorker` → must implement `eat()` but robots don't eat → throws `UnsupportedOperationException` ❌

### The Fix

Split the interface:
- `Workable` → `work()`
- `Eatable` → `eat()`

`HumanWorker` implements both. `RobotWorker` implements only `Workable`.

Clean, no forced methods, no fake implementations.

### Interview Signal

Whenever a class implements an interface but leaves some methods empty or throws `UnsupportedOperationException`, say:  
*"This is an ISP violation. The interface is too broad. I'll split it into focused, role-specific interfaces."*

---

## D — Dependency Inversion Principle (DIP)

### The Idea

> High-level modules should **not depend on low-level modules.**  
> Both should depend on **abstractions.**

The top-level business logic shouldn't be wired directly to the specific tools it uses.

### Real World Analogy

A lamp (high-level) shouldn't be hardwired to a specific power station (low-level). Instead, it plugs into a **standard socket (abstraction)**. You can swap the power source — solar, grid, generator — without rewiring the lamp.

### Where it Breaks

`UserService` directly creates a `MySQLDatabase` object inside itself. If you switch to PostgreSQL or MongoDB, you must open and rewrite `UserService` — the high-level business class. That's fragile and wrong.

### The Fix

Define a `Database` interface. `UserService` depends on the interface, not the concrete class. You inject whichever database you want at runtime (constructor injection).

```
UserService → Database (interface)
                  ↑               ↑
          MySQLDatabase    PostgreSQLDatabase
```

Now swapping the database is a one-line change at the composition root.

### Connection to Dependency Injection

DIP is the *principle*. Dependency Injection (DI) is the *technique* to implement it. Frameworks like Spring use DI to wire dependencies automatically — all built on DIP.

### Interview Signal

If you see a high-level class directly instantiating a low-level class using `new`, say:  
*"This is a DIP violation. I'll introduce an interface and inject the dependency — this makes the system testable and flexible."*

---

## How SOLID Principles Connect

```
SRP  →  Each class does one thing (clean starting point)
OCP  →  Add new behavior by extension (not by modification)
LSP  →  Subclasses must honor the parent contract
ISP  →  Interfaces stay small and focused
DIP  →  High-level logic depends on interfaces, not implementations
```

They work together. Violating one often leads to violating others.  
Following all five results in a codebase that is genuinely easy to grow and change.

---

## Real Systems That Use All 5

| System | SOLID Principles at Work |
|--------|--------------------------|
| Payment Gateway | OCP (new payment types), DIP (inject payment provider) |
| Notification System | OCP (new channels), ISP (channel-specific interfaces) |
| Parking Lot | SRP (separate ticket, payment, gate logic) |
| Uber Cab System | DIP (swap map provider), LSP (driver types) |
| Logger System | OCP (add new log targets), DIP (inject log destination) |

---

## Interview Cheat Sheet

| Principle | Smells That Indicate Violation |
|-----------|-------------------------------|
| SRP | Class has more than 1 reason to change |
| OCP | Modifying existing class to add new feature |
| LSP | Child throws exception or returns null from inherited method |
| ISP | Class implements interface but leaves methods empty |
| DIP | High-level class uses `new` to create low-level class |

---

> 📌 Next: [Strategy Pattern](strategy-pattern.md) — solving duplicate behavior across sibling classes
