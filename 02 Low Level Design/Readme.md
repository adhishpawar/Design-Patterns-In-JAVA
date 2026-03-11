# 🗺️ Low Level Design (LLD) — Complete Roadmap

> A structured learning path from fundamentals to FAANG-level LLD interviews.  
> Based on real interview patterns at Amazon, Uber, Flipkart, Google.

---

## 📋 Index of Topics

---

### 🔷 Phase 1 — Foundations (Start Here)

| # | Topic | Status |
|---|-------|--------|
| 1.1 | What is LLD vs HLD? | ⬜ |
| 1.2 | Object Oriented Programming (OOP) Refresher | ⬜ |
| 1.3 | Classes, Objects, Abstraction, Encapsulation | ⬜ |
| 1.4 | Inheritance vs Composition | ⬜ |
| 1.5 | Polymorphism — Compile time & Runtime | ⬜ |
| 1.6 | Interfaces vs Abstract Classes | ⬜ |
| 1.7 | UML Diagrams — Class Diagrams Basics | ⬜ |

---

### 🔷 Phase 2 — SOLID Principles

| # | Topic | Status |
|---|-------|--------|
| 2.1 | [S] Single Responsibility Principle (SRP) | ✅ |
| 2.2 | [O] Open Closed Principle (OCP) | ✅ |
| 2.3 | [L] Liskov Substitution Principle (LSP) | ✅ |
| 2.4 | [I] Interface Segregation Principle (ISP) | ✅ |
| 2.5 | [D] Dependency Inversion Principle (DIP) | ✅ |
| 2.6 | SOLID applied together — Notification System | ✅ |

📁 Notes: [`docs/solid-principles.md`](docs/solid-principles.md)

---

### 🔷 Phase 3 — Design Patterns (Creational)

| # | Topic | Status |
|---|-------|--------|
| 3.1 | Singleton Pattern | ⬜ |
| 3.2 | Factory Method Pattern | ⬜ |
| 3.3 | Abstract Factory Pattern | ⬜ |
| 3.4 | Builder Pattern | ⬜ |
| 3.5 | Prototype Pattern | ⬜ |

---

### 🔷 Phase 4 — Design Patterns (Structural)

| # | Topic | Status |
|---|-------|--------|
| 4.1 | Adapter Pattern | ⬜ |
| 4.2 | Decorator Pattern | ⬜ |
| 4.3 | Facade Pattern | ⬜ |
| 4.4 | Proxy Pattern | ⬜ |
| 4.5 | Composite Pattern | ⬜ |

---

### 🔷 Phase 5 — Design Patterns (Behavioural)

| # | Topic | Status |
|---|-------|--------|
| 5.1 | Strategy Pattern | ✅ |
| 5.2 | Observer Pattern | ✅ |
| 5.3 | Command Pattern | ⬜ |
| 5.4 | Chain of Responsibility | ⬜ |
| 5.5 | Template Method Pattern | ⬜ |
| 5.6 | State Pattern | ⬜ |
| 5.7 | Iterator Pattern | ⬜ |

📁 Notes:  
[`docs/strategy-pattern.md`](docs/strategy-pattern.md)  
[`docs/observer-pattern.md`](docs/observer-pattern.md)

---

### 🔷 Phase 6 — LLD Interview Problems (Beginner)

| # | System | Key Patterns |
|---|--------|-------------|
| 6.1 | Parking Lot System | OOP, Strategy |
| 6.2 | Library Management System | OOP, SRP |
| 6.3 | ATM System | State Pattern |
| 6.4 | Vending Machine | State Pattern |
| 6.5 | Tic Tac Toe | OOP |

---

### 🔷 Phase 7 — LLD Interview Problems (Intermediate)

| # | System | Key Patterns |
|---|--------|-------------|
| 7.1 | Food Delivery System (Swiggy/Zomato) | Strategy, Observer |
| 7.2 | Splitwise | OOP, SRP |
| 7.3 | Notification System | Observer, Strategy |
| 7.4 | Cab Booking System (Uber) | Strategy, Observer |
| 7.5 | Hotel Booking System | Builder, Factory |
| 7.6 | Movie Ticket Booking (BookMyShow) | Factory, Observer |

---

### 🔷 Phase 8 — LLD Interview Problems (Advanced / FAANG)

| # | System | Key Patterns |
|---|--------|-------------|
| 8.1 | Elevator System | State, Strategy |
| 8.2 | Chess Game | OOP, Command |
| 8.3 | Snake and Ladder | OOP |
| 8.4 | Logger System | Singleton, Chain of Responsibility |
| 8.5 | Payment Gateway | Strategy, Decorator |
| 8.6 | Rate Limiter | Strategy |
| 8.7 | Cache System (LRU/LFU) | Strategy, Decorator |
| 8.8 | YouTube / Amazon Notification | Observer |

---

### 🔷 Phase 9 — Relationships & UML Deep Dive

| # | Topic | Status |
|---|-------|--------|
| 9.1 | Association ( `—` ) | ⬜ |
| 9.2 | Aggregation ( `◇—` ) | ⬜ |
| 9.3 | Composition ( `◆—` ) | ⬜ |
| 9.4 | Inheritance / IS-A ( `--|>` ) | ⬜ |
| 9.5 | Has-A relationship | ⬜ |
| 9.6 | Dependency ( `- ->` ) | ⬜ |
| 9.7 | Full Class Diagram practice | ⬜ |

---

### 🔷 Phase 10 — Interview Preparation

| # | Topic |
|---|-------|
| 10.1 | How to approach an LLD interview question |
| 10.2 | Clarifying requirements — what to ask |
| 10.3 | Identifying entities and relationships |
| 10.4 | Choosing right design patterns |
| 10.5 | Writing clean, extensible code |
| 10.6 | Explaining your design out loud |
| 10.7 | Common LLD interview mistakes |

---

## 📁 Repository Structure

```
LLD-Learning/
│
├── README.md                  ← This roadmap
│
├── docs/
│   ├── solid-principles.md    ← SOLID (all 5 principles)
│   ├── strategy-pattern.md    ← Strategy Design Pattern
│   └── observer-pattern.md    ← Observer Design Pattern
│
├── phase-1-oops/
├── phase-2-solid/
├── phase-3-creational/
├── phase-4-structural/
├── phase-5-behavioural/
└── phase-6-8-problems/
```

---

## 🧠 Interview Cheat Sheet

| Principle / Pattern | One Line |
|--------------------|----------|
| SRP | One class → one job |
| OCP | Add new without touching old |
| LSP | Child must not break parent's contract |
| ISP | Don't force unused methods on a class |
| DIP | Depend on interfaces, not concrete classes |
| Strategy | Swap algorithms at runtime |
| Observer | One change → notify all dependents |
| Singleton | Only one instance ever |
| Factory | Let subclass decide which object to create |
| Builder | Construct complex objects step by step |
| Decorator | Add behavior without changing the class |

---

> ⭐ Star this repo if it helps your LLD journey!
