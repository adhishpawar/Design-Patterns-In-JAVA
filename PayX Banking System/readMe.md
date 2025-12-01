# PayX Design Patterns Documentation

This repository documents the design patterns implemented in the PayX Banking / FinTech system using Java,
simulating Spring Boot internal behaviors without using Spring itself.

## Patterns Implemented

- Singleton
- Factory / Factory Method
- Strategy
- Observer
- Chain of Responsibility

Each pattern document contains:

✔ Real-life problem before pattern  
✔ Explanation of pattern  
✔ Why needed  
✔ How Spring uses it internally  
✔ Our Java implementation  
✔ Final learning takeaways

---

# Goal of this project

We are implementing a **tiny Spring-like framework**:

- Our own Bean Factory
- Our own Singleton Container
- Our own Event system
- Our own DI
- Our own CoR pipeline

Then we will build the PayX Banking System using these patterns.

---

# Directory Structure

payx_patterns/  
│  
├── readMe.md
└── Doc/  
├── singleton.md  
├── factory.md  
├── strategy.md  
├── observer.md  
└── chain_of_responsibility.md


Continue reading in `Doc/…`