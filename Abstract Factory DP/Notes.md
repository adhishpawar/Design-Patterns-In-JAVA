# Abstract Factory Design Pattern

The **Abstract Factory Design Pattern** is an extension of the Factory Design Pattern. It provides an interface for creating families of related or dependent objects without specifying their concrete classes.

## Key Features:
1. Adds another level of abstraction by introducing an **Abstract Factory Class**.
2. Helps create objects for **multiple related classes** while keeping the creation logic flexible and extensible.
3. Ensures consistency when working with multiple families of objects.

## Difference Between Factory and Abstract Factory Design Patterns:

| **Aspect**          | **Factory Design Pattern**                                    | **Abstract Factory Design Pattern**                     |
|----------------------|--------------------------------------------------------------|---------------------------------------------------------|
| **Purpose**          | Creates objects of a single type based on input.             | Creates families of related objects through factories.  |
| **Abstraction Level**| Single factory method for object creation.                   | Uses abstract factory classes for more flexibility.     |
| **Use Case**         | When only one type of object is required.                    | When multiple related types of objects are needed.      |
| **Scalability**      | Less scalable when multiple object families are needed.      | Highly scalable for creating families of objects.       |

---

## Flow of Execution:
1. Define an **interface (e.g., `Employee`)** that outlines the common methods for all objects.
2. Create an **Abstract Factory Class** (e.g., `EmployeeAbstractFactory`) that defines the method `createEmployee`.
3. Implement **Concrete Factories** (e.g., `AndroidDevFactory`, `WebDevFactory`, `ManagerFactory`) that override the `createEmployee` method to return specific implementations.
4. Use a **Client Class** to request objects by passing the required factory instance to a factory helper method.
