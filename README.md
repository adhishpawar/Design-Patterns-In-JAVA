### Design-Patterns-In-JAVA
1) Creational Patterns — how objects are created
	
| Pattern          | Real-life Meaning                  | Real Backend Example                         |
| ---------------- | ---------------------------------- | -------------------------------------------- |
| Singleton        | Only 1 instance exists             | DB connection manager, config loader         |
| Factory          | Decide object creation at runtime  | PaymentGatewayFactory → UPI, Card, Wallet    |
| Abstract Factory | Factory of factories               | UI Theme factory (Mobile, Web), DAO Factory  |
| Builder          | Build complex objects step-by-step | Building Response DTOs, Query Builders       |
| Prototype        | Copy existing object               | Cloning objects, deep copy of request states |
2) Structural Patterns — how classes are composed

|Pattern|Real-life Meaning|Real Backend Example|
|---|---|---|
|Adapter|Convert one interface to another|Convert external API format to internal DTO|
|Decorator|Add features dynamically|Adding logging, auditing, compression to services|
|Facade|Simple interface to complex system|Service layer over 10 subsystems|
|Proxy|A placeholder object that controls access|Authentication & Authorization proxy|
|Composite|Tree / hierarchy structures|Menus, Departments, File structure|
|Flyweight|Memory optimization for repeated objects|Caching objects, shared constants|
|Bridge|Decouple abstraction from implementation|Notification service: SMS / Email / Push|
3) Behavioral Patterns — how objects communicate

| Pattern                 | Real-life Meaning                  | Real Backend Example                     |
| ----------------------- | ---------------------------------- | ---------------------------------------- |
| Strategy                | Change algorithm dynamically       | Payment validation strategies, sorting   |
| Observer                | Notify listeners on changes        | Event-driven notifications, stock alerts |
| Command                 | Encapsulate request as object      | Queued tasks, Kafka commands             |
| State                   | Behavior changes with state        | Order status: CREATED → PACKED → SHIPPED |
| Chain of Responsibility | Process pipeline                   | Middleware pipeline, HTTP filter chain   |
| Template                | Skeleton with extension points     | Base service with customizable steps     |
| Iterator                | Controlled traversal of collection | Custom collections, pagination           |
| Mediator                | Central communication hub          | Chat system, messaging router            |

## MUST KNOW 

✔ Singleton  
✔ Factory / Factory Method  
✔ Builder  
✔ Strategy  
✔ Observer  
✔ Chain of Responsibility  
✔ Template Method  
✔ Adapter  
✔ Facade  
✔ Proxy

# Real-Life importance — WHAT PROBLEM THEY SOLVE

## 1: Singleton solves:

- Too many connections
- Memory waste
- Repeated object creation  
    → ensures ONE global object instance  
    Used in:
- Logger
- Configuration loader
- Cache instance
- Database connector

## 2: Factory solves:

- Object creation complexity
    
- Need to switch implementations
    
- Avoid coupling
    

Example:
	
	`NotificationFactory → create(“EMAIL”)`
	
	Returns `EmailNotificationService`  
	Instead of:
	
	`new EmailNotificationService()`


## 3. Strategy solves:

- Algorithm switching based on condition
    

Example:

`PaymentStrategy   → UpiPaymentStrategy   → DebitCardPaymentStrategy   → CreditCardPaymentStrategy`

You can switch at runtime.

## 4: Chain of Responsibility solves:

- Sequential pipeline processing
    
- Example:  
    Request → Validation → Logging → Authentication → Database → Response
    

This is literally how:

✔ Spring Handler Interceptors  
✔ Servlet Filters  
✔ Spring Security Filter Chain

work internally.
