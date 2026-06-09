# Observer Design Pattern — LLD Notes

> A behavioural design pattern.  
> Notes focused on concept clarity and interview readiness.  
> No code — pure understanding, mental models, and real-world analogies.

---

## The Core Problem It Solves

You have an object whose **state changes**.  
Multiple other objects need to **react** to that change.

How do you notify all of them without tightly coupling the source to each subscriber?

Observer Pattern says:  
> **Let interested parties register themselves. When state changes, notify all of them automatically.**

---

## What is the Observer Pattern?

A pattern that defines a **one-to-many dependency** between objects:

- One **Observable** (also called Publisher or Subject)
- Many **Observers** (also called Subscribers or Listeners)

When the Observable changes state → all registered Observers are automatically notified and updated.

---

## Real World Analogy

### YouTube Subscriptions

- **YouTube Channel** = Observable
- **Subscribers** = Observers
- When a new video is uploaded (state change), YouTube notifies all subscribers
- Subscribers can subscribe or unsubscribe anytime
- The channel doesn't know *what* each subscriber does when notified — it just notifies

### Amazon Notify Me

- Product is out of stock
- Users click "Notify Me" → they register as Observers
- When stock is added (state change) → all registered users get notified via SMS, Email, App

---

## The Two Core Participants

### Observable (Publisher)

Responsible for:
- Maintaining a **list of Observers**
- Providing **add / remove** methods for registration
- Calling **notify** on all registered Observers when state changes

### Observer (Subscriber)

Responsible for:
- Implementing an **update()** method
- Defining what to do when notified
- Optionally holding a reference to the Observable to read the new state

---

## How It Flows

```
[Observable State Changes]
         ↓
[Calls notifyObservers()]
         ↓
[Iterates through observer list]
         ↓
[Calls update() on each Observer]
         ↓
[Each Observer reacts independently]
```

---

## The Weather Station Example (From Your Notes)

### Setup

- `WeatherStation` is the Observable
- It tracks temperature
- When temperature updates → all displays must show the new value

### Observers

- `MobileDisplay` — shows temperature on mobile app
- `TVDisplay` — shows temperature on TV screen

### Flow

1. `WeatherStation.setTemperature(30)` is called
2. Internally it calls `notifyObservers()`
3. Both `MobileDisplay` and `TVDisplay` receive `update()`
4. Each display calls `weatherStation.getTemperature()` to fetch the new value
5. Each displays it in its own way

Adding a new display (e.g. `SmartWatchDisplay`)? Just create it and register it. Weather station doesn't change.

---

## The Amazon Stock Alert Example (From Your Notes)

### Setup

- `ProductStockObservable` tracks iPhone stock
- Users register via "Notify Me"

### Observers

- `EmailAlertObserver` — sends email notification
- `SmsAlertObserver` — sends SMS notification

### Flow

1. Admin calls `setStockCount(10)` — adds 10 units
2. Since stock > 0 → `notifyObservers()` is triggered
3. Each registered observer's `update()` is called
4. `EmailAlertObserver` sends email
5. `SmsAlertObserver` sends SMS

Different observers do completely different things — the Observable doesn't care what they do.

---

## The Design: Constructor Injection in Observers

### Why Observers Hold a Reference to Observable

In your notes, you observed:

```
MobileDisplayObserver(WeatherStationObservable obj)
   this.obj = obj
```

The Observer stores a reference to the Observable so it can call `getData()` / `getTemperature()` inside `update()`.

### Two Approaches for Passing Data

**Push approach:** Observable passes data as a parameter to `update(data)`  
- Observable controls what data is shared
- Less flexible — fixed contract

**Pull approach:** Observer holds reference to Observable, pulls data itself inside `update()`  
- More flexible — Observer decides what to read
- Slightly more coupled (Observer knows Observable type)
- The approach used in your Weather Station example

### Loose Coupling Benefit

Because the Observer stores the **interface reference** (not the concrete class), you can swap the Observable implementation without changing the Observer. This is DIP at work.

---

## Key Concepts to Remember

### The Registration Pattern

```
Observable
  ├── add(Observer)     ← registration
  ├── remove(Observer)  ← deregistration
  └── notifyObservers() ← triggers all update() calls
```

### Disconnected Business Logic

Each observer has its own business logic inside `update()`:
- `EmailAlertObserver` → knows how to send email
- `SmsAlertObserver` → knows how to send SMS

The Observable is **completely unaware** of these implementations. That's what makes the system truly loosely coupled.

### Dynamic Subscription

Observers can subscribe or unsubscribe **at runtime** — not hardcoded at compile time. This makes the system dynamic and flexible.

---

## When to Use Observer Pattern

Use it when:
- One object's state change needs to trigger reactions in **multiple** other objects
- You don't know **how many** objects need to react (dynamic)
- You want the source and the listeners to be **loosely coupled**
- You're building event-driven or notification-driven systems
- Different objects react to the **same event** in different ways

---

## Real Systems That Use Observer Pattern

| System | Observable | Observers |
|--------|-----------|-----------|
| Amazon | Product Stock | Email, SMS, App Push |
| Stock Market App | Stock Price | Price Alert, Graph, News Feed |
| YouTube | Channel (new upload) | Subscriber notifications |
| Kafka / RabbitMQ | Topic / Queue | Multiple consumers |
| UI Frameworks (React) | State | Component re-renders |
| Weather App | Weather Service | Mobile, TV, Web displays |
| Uber | Trip Status | Driver app, Rider app, Support |

---

## Pattern Architecture (UML Sketch)

```
        «interface»
         Observable
       add / remove / notify
              |
              |
   ProductStockObservable
   WeatherStationObservable
              |
      notifyObservers() ————————→ «interface»
                                    Observer
                                    update()
                                      |
                              ________|________
                              |               |
                        EmailAlert        SmsAlert
                        Observer          Observer
```

---

## Observer vs Other Patterns

| Pattern | Key Difference |
|---------|---------------|
| Observer | One state change notifies **many** automatically |
| Strategy | **Swap** behavior/algorithm of an object |
| Mediator | Central coordinator instead of direct notification |
| Event Bus | Observer at system scale (decoupled via bus) |

---

## Interview Answer Template

> *"Observer pattern defines a one-to-many relationship between objects.*
>
> *When the state of one object (Observable) changes, all registered observers are automatically notified via their update() method.*
>
> *The Observable only knows the Observer interface — it has no idea what each observer actually does. This makes the system loosely coupled and dynamically extensible.*
>
> *Classic examples: Amazon stock alerts, YouTube notifications, Kafka topic consumers, UI state management."*

---

## Common Mistakes to Avoid

| Mistake | Why It's Wrong |
|---------|---------------|
| Observable knowing concrete Observer types | Tight coupling — defeats the pattern |
| Forgetting to deregister observers | Memory leaks in long-running systems |
| Observers modifying Observable state in update() | Infinite notification loops |
| Using Observer when only 1 listener exists | Overkill — simple callback is enough |
| Thread-unsafe notification list | Race conditions in concurrent systems |

---

## Advanced Insight: Observer at Scale

In real production systems, the Observer pattern evolves into:
- **Event Bus** (in-process, e.g. Guava EventBus)
- **Message Queue** (distributed, e.g. Kafka, RabbitMQ)
- **WebSockets** (real-time browser push)

All are conceptually the same idea: *one state change → many subscribers notified asynchronously.*

---

> 📌 Previous: [Strategy Pattern](strategy-pattern.md)  
> 📌 Back to Roadmap: [LLD Roadmap](../LLD-ROADMAP.md)
