# 1) What is Builder Pattern?

A **creational design pattern** used to construct **complex objects step-by-step**  
without requiring multiple constructors or telescoping constructors.

> Builder allows you to create objects with many parameters while keeping code readable & safe.

---

# 🟥 2) The Real Problem We Are Solving

## ❌ Problem: Too many parameters

Imagine a `PaymentRequest` in PayX:

`accountId amount method currency location ipAddress deviceId timestamp channel riskScore merchantCategory`

If we build via constructor:

`new PaymentRequest(a, b, c, null, null, null, x, y, z, 0.1, "anything")`

→ **Unreadable**  
→ **Error-prone**  
→ **Forgetting arguments leads to bugs**  
→ **Order of parameters matters**  
→ **Cannot skip optional fields**

This is the real-world **TELSCOPING CONSTRUCTOR PROBLEM**.

---

## ❌ Problem 2: Mutability

Using setter-based objects:

```
PaymentRequest req = new PaymentRequest(); 
req.setAccId(...); 
req.setAmount(...); 
req.setIp(...);
```

→ object can change anywhere → dangerous in banking/fintech  
→ concurrency issues  
→ integrity issues

---

# ✔ Builder Pattern SOLVES this

- Readable
- Immutable
- Optional params easy to add
- No telescoping constructors
- No setter mutability

---

# 🧠 3) Where Spring uses Builder Pattern?

Spring and Spring Boot use Builder everywhere:

### ✔ WebClient builder

``` 
WebClient client = WebClient.builder()  
									.baseUrl("https://api")         
									.defaultHeader("key", "value")         
									.build();							
```
									
### ✔ MockMvc builder

`MockMvcBuilders.standaloneSetup(controller)`

### ✔ UriComponentsBuilder

`UriComponentsBuilder.fromUriString(url)`

### ✔ ResponseEntity builder

`ResponseEntity.ok().body(data)`

### ✔ Security HttpSecurity builder

`http.csrf().disable()     .authorizeHttpRequests()     ...`

Builder is the **backbone** of Spring Boot’s declarative & readable code style.

---

# 🏦 4) Why Builder is PERFECT for PayX?

PayX has a LOT of request objects:

✔ PaymentRequest  
✔ FundTransferRequest  
✔ CustomerOnboardingRequest  
✔ KYCRequest  
✔ UPICollectRequest  
✔ CardPaymentRequest  
✔ RiskAnalysisRequest  
✔ FraudAlertRequest

These often contain 10–20 fields.

Builder makes them:

- immutable
- readable
- safe
- extensible
- version-friendly

These include:

### ✔ PayXFacade input

### ✔ API layer DTO

### ✔ Internal domain objects

### ✔ Commands to workflow engines

### ✔ Event model building

---
