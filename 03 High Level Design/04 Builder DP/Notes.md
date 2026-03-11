## Builder Design Pattern 

while Creating object contain may Attributes there are many problem exists:

1.We have to pass many arugumnets to create object 
2.Some parameter might optional
3.factory class takes all responsibility for creating Object. if the Object is Heavy then all complexity is the part of factory class

So in builder pattern we create object setp by step and Finally return final object with desired values of Attributes


The **Builder Design** Pattern is a creational design pattern used to construct complex objects step by step. Unlike a telescoping constructor, it provides a clear separation between object construction and representation, improving code readability and flexibility.


## Overview
This code demonstrates the **Builder Design Pattern**, which simplifies object creation by providing a step-by-step approach. It encapsulates the construction logic in a builder class while keeping the main object immutable.

## Flow Explanation

### 1. **`User` Class**
   - Represents an immutable user object with the following properties:
     - `userId`
     - `userName`
     - `emailId`
   - The `User` class has:
     - A **private constructor** accepting a `UserBuilder` instance.
     - **Getters** for its properties.
     - A **toString()** method for display purposes.

### 2. **`UserBuilder` Class**
   - A static nested builder class inside `User`.
   - Contains mutable fields (`userId`, `userName`, `emailId`) used during object construction.
   - Provides:
     - **Setter methods** (e.g., `setUserId`) that return the builder instance for chaining.
     - A **static method `builder()`** to create a new `UserBuilder` instance.
     - A **`build()` method** to create a `User` object using the current builder state.

### 3. **Usage in `Main`**
   - Example 1: Create a `User` using the `UserBuilder` constructor directly:
     ```java
     User user = new User.UserBuilder()
                        .setEmailId("adhishpawar@gmail.com")
                        .setUserId("Adhish9008")
                        .setUserName("Adhish")
                        .build();
     ```
   - Example 2: Create a `User` using the static `builder()` method:
     ```java
     User user2 = User.UserBuilder.builder()
                                  .setUserId("XYZ123")
                                  .setUserName("XYZ")
                                  .build();
     ```
   - Each `build()` call creates an immutable `User` object.

### 4. **Output**
   - `System.out.println(user)`:
     ```
     Adhish : adhishpawar@gmail.com : Adhish9008
     ```
   - `System.out.println(user2)`:
     ```
     XYZ : null : XYZ123
     ```

## Key Features
- **Immutable Object**: Ensures `User` cannot be modified after creation.
- **Flexible Construction**: Supports chaining and partial data initialization.
- **Clean Code**: Separates object-building logic into a dedicated builder.

## Advantages
- Useful for creating complex objects with optional parameters.
- Improves code readability and maintenance.

## Notes
- The `builder()` method provides an additional way to initialize the builder, improving usability.
- To customize this further, validations can be added in the `build()` method.