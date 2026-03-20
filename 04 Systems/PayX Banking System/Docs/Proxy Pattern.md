# 🟪 1) What is Proxy Pattern?

**Definition:**

> Proxy is a structural design pattern that provides a substitute or placeholder for another object to control access to it.

In simple words:

> "You write a special class that represents another class — but with extra logic."

---

# ❗ 2) What Problem Are We Solving?

When calling a method in a critical system like PayX, often we need:

✔ Logging  
✔ Security checks  
✔ Rate limiting  
✔ Caching  
✔ Audit tracking  
✔ Performance profiling  
✔ Lazy loading  
✔ Remote access  
✔ Access control  
✔ Transaction handling  
✔ Retry logic

WITHOUT modifying the real service code.

If you write logic directly inside springLite.ioc.core services:

- Code becomes dirty
- Violates SRP
- Hard to test
- Hard to maintain
- Hard to disable certain behaviors
- Mix of business + infra logic

### Example Problem:

`PaymentService.transfer()` should do **ONLY business logic**.

But we also want:

- Log method call
- Check user authorization
- Perform fraud block
- Retry on failure
- Start/commit DB transaction
- Publish audit logs
- Measure execution time

If we add all this **inside** `PaymentService`, the class becomes a monster.

---

# ✔ 3) Solution — Proxy Pattern

Proxy wraps the real object and adds extra behavior:

`Client → Proxy → Real Service`

Example:

`PaymentServiceProxy logs, checks security, validates… then calls real PaymentService`

---

# 🏛️ 4) Where does Spring use Proxy?

Spring is **built on Proxy pattern**.

### 1. @Transactional

Creates a springLite.aop.proxy around your service:

`Before: beginTx Call real method After: commit/rollback`

### 2. @Async

Wraps method inside springLite.aop.proxy → executes in separate thread.

### 3. @Cacheable

Proxy checks cache → returns cached data  
only calls real method if needed.

### 4. Spring Security

All requests pass through **security proxies**.

### 5. Hibernate Lazy Loading

Returns springLite.aop.proxy instead of real entity → loads when accessed.

---
