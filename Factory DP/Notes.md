# Factory Design Pattern

The **Factory Design Pattern** is a **creational design pattern** that provides a way to create objects without exposing the creation logic to the client. Instead, the client uses a **factory method** to obtain an instance of the required class.

## Flow of Execution:
1. **Client** requests an object by calling the factory method.
2. **Factory** determines the subclass to instantiate based on input or requirements.
3. **Factory** creates and returns an object of the appropriate subclass.
4. **Client** interacts with the object through the interface or superclass without knowing its exact type.

## Use Case:
- Create objects of subclasses dynamically based on input or configuration.
- Avoid tight coupling between the client code and the specific classes.

## Advantages:
1. **Abstraction**: Promotes creating objects for interfaces or abstract classes.
2. **Loose Coupling**: Decouples object creation logic from client code.
3. **Reusability**: Centralized creation logic improves maintainability.
4. **Scalability**: Simplifies adding new subclasses without modifying existing code.
