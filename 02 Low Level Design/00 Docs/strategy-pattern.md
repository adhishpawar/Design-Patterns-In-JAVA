# Strategy Design Pattern — LLD Notes

> A behavioural design pattern.  
> Notes focused on concept clarity and interview readiness.  
> No code — pure understanding, mental models, and real-world analogies.

---

## The Core Problem It Solves

You have a family of related classes (siblings in an inheritance hierarchy).  
Some of them share the **same behavior** — but that behavior lives in multiple places as **duplicate code**.

As the system grows, maintaining this becomes a nightmare.

Strategy Pattern says:  
> **Take the behavior out of the class. Encapsulate it. Inject it.**

---

## What is the Strategy Pattern?

A pattern where you:

1. Define a **family of algorithms** (behaviors)
2. Encapsulate each one into its own class
3. Make them **interchangeable**
4. Inject the right one into the object that needs it

The object doesn't decide the behavior — it **delegates** it to the injected strategy.

---

## Real World Analogy

Think of a **navigation app** like Google Maps.

The app can give directions by:
- Car (fastest route)
- Walking (shortest path)
- Bicycle (cycle-friendly roads)
- Public transport (bus/metro schedule)

The app itself doesn't change. You just **switch the routing strategy** based on what the user picks.

Each routing algorithm is a separate, interchangeable strategy.

---

## The Vehicle Problem (From Your Notes)

### The Setup

You have a `Vehicle` base class with a `drive()` method.  
Three child classes:
- `PassengerVehicle`
- `SportsVehicle`
- `OffRoadVehicle`

### The Problem

`SportsVehicle` and `OffRoadVehicle` both have the exact same special driving behavior.  
You copy-paste the same logic into both classes.

Tomorrow, add `RacingCar`, `MountainVehicle`, `DuneBuggy` — all might share similar drive behavior.

**The result:**
- Same code scattered across multiple sibling classes
- Change in one place means change in all
- Hard to maintain as system scales
- Violates DRY (Don't Repeat Yourself)

This is the **sibling duplication problem** — siblings having the same capabilities but duplicating code to express them.

### Why Inheritance Alone Can't Fix This

You might think: *"Put it in the parent!"*  
But not all vehicles need that special drive. `PassengerVehicle` uses normal driving.

Putting special drive in `Vehicle` would force it on every child — that's an ISP violation.

You could also override at each level — but that brings back duplicate code.

Inheritance alone cannot cleanly solve sibling-level shared behavior.

---

## The Strategy Pattern Fix

### Mental Model

Instead of embedding behavior **inside** the class hierarchy, you:

1. Create a **DriveStrategy interface** (the abstraction)
2. Create concrete strategy classes: `NormalDriveStrategy`, `SportsDriveStrategy`
3. Give `Vehicle` a reference to a `DriveStrategy` object
4. Inject the right strategy through the **constructor**

### How It Flows

```
Vehicle
  └── has-a → DriveStrategy (interface)
                    ├── NormalDriveStrategy
                    └── SportsDriveStrategy

PassengerVehicle → injects NormalDriveStrategy
SportsVehicle    → injects SportsDriveStrategy
OffRoadVehicle   → injects SportsDriveStrategy  ← same strategy, no duplication!
```

`SportsDriveStrategy` is written **once** and shared by both SportsVehicle and OffRoadVehicle.

### The Key Relationship

- `Vehicle` **has-a** `DriveStrategy` → composition (`——`)
- `Vehicle` **is-a** parent of child classes → inheritance (`--|>`)

Strategy pattern shifts from *IS-A* thinking to *HAS-A* thinking for behavior.

---

## Network Routing Problem (Real World Extension)

### The Setup

A network application routes data packets through different channels:
- WiFi
- Bluetooth
- 5G
- Satellite
- Mesh Network

### The Problem Without Strategy

You'd write a massive `if-else` or `switch` block:
```
if type == "wifi" → route via wifi
else if type == "bluetooth" → route via bluetooth
else if type == "5G" → route via 5G
```

Adding a new channel = modifying this class = OCP violation + maintenance nightmare.

### The Strategy Pattern Fix

- `RoutingStrategy` interface → defines `routeData(packet)`
- `WifiRouting`, `BluetoothRouting`, `FiveGRouting` → separate concrete classes
- `NetworkRouter` → holds a `RoutingStrategy` reference, delegates routing to it

Adding `SatelliteRouting`? Just create a new class. Zero changes to `NetworkRouter`.

This is **OCP + Strategy working together** — the most common combination in real systems.

---

## Key Concepts to Remember

### Strategy vs Inheritance

| Approach | Problem |
|----------|---------|
| Inheritance for behavior | Sibling duplication, tight coupling |
| Strategy Pattern | Behavior separated, reusable, injectable |

### The Three Parts of Every Strategy Pattern

1. **Strategy Interface** — defines the contract for the behavior
2. **Concrete Strategies** — each implementation of the behavior
3. **Context Class** — holds a reference to a Strategy, delegates to it

### Constructor Injection

The child class passes the right strategy to the parent via `super(new XxxStrategy())`. This is constructor injection — a DIP-compliant, clean way to wire behavior.

---

## When to Use Strategy Pattern

Use it when:
- Multiple classes differ only in their **behavior**
- You need to **swap algorithms** at runtime
- You want to avoid `if-else` / `switch` for selecting behavior
- Sibling classes are **duplicating the same logic**
- You need to follow OCP for adding new behaviors

---

## Real Systems That Use Strategy Pattern

| System | Strategy Used For |
|--------|--------------------|
| Payment Gateway | Choosing payment method (Card, UPI, Wallet) |
| Sorting Library | Choosing sorting algorithm (QuickSort, MergeSort) |
| Compression Tool | Choosing compression format (ZIP, RAR, GZIP) |
| Network Router | Choosing routing protocol |
| Logging Framework | Choosing log destination (File, Console, Remote) |
| Discount Engine | Choosing discount calculation strategy |

---

## Pattern Architecture (UML Sketch)

```
        «interface»
       DriveStrategy
            |
    ________|________
    |                |
NormalDrive     SportsDrive
Strategy         Strategy

Vehicle
  └── driveStrategy: DriveStrategy
        |
        ├── PassengerVehicle → NormalDriveStrategy
        ├── SportsVehicle    → SportsDriveStrategy
        └── OffRoadVehicle   → SportsDriveStrategy
```

---

## Interview Answer Template

> *"Strategy pattern is used when multiple classes share the same behavior but implementing it via inheritance leads to code duplication.*
>
> *Instead of placing behavior inside the class, we extract it into a separate strategy interface with concrete implementations. The context class holds a reference to the strategy and delegates to it.*
>
> *This eliminates duplication, follows OCP (you can add new strategies without changing the context), and enables runtime behavior switching."*

---

## Common Mistakes to Avoid

| Mistake | Why It's Wrong |
|---------|---------------|
| Putting all strategies inside the context class | Defeats the purpose — still tightly coupled |
| Using Strategy when simple inheritance works | Over-engineering |
| Forgetting to inject strategy — hardcoding inside class | Breaks DIP, makes it untestable |
| Making strategy stateful | Strategies should ideally be stateless |

---

## Quick Comparison: Strategy vs Other Patterns

| Pattern | Key Difference |
|---------|---------------|
| Strategy | Swaps **how** something is done (algorithm) |
| State | Swaps **what** the object does based on internal state |
| Template Method | Defines skeleton, lets subclass fill in steps |
| Command | Encapsulates an **action** as an object |

---

> 📌 Previous: [SOLID Principles](solid-principles.md)  
> 📌 Next: [Observer Pattern](observer-pattern.md)
