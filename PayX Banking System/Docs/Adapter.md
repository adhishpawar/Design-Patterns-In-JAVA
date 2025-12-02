# 🟥 1) What is Adapter Pattern?

**Concept:**  
Adapter is a **structural design pattern** that makes incompatible interfaces work together.

### Simple definition:

> **Convert the interface of one class into another interface clients expect.**

Or in plain English:

> “I already built my system, but the external system sends data in a different format. I must convert it.”

---

# ❗ 2) What problem does Adapter solve?

### 📌 Problem:

Your system has an expected input/output format.

But the third-party or external system gives:

- different method names
- different JSON fields
- different parameter order
- different class structure
- incompatible formats
### Example:

PayX expects:
`bank.transfer(amount, fromAcc, toAcc)`
But external bank API is like:
`external.sendMoney(source, destination, moneyInPaise)`

This causes:
❌ Incompatibility  
❌ Code breaks  
❌ Cannot integrate external APIs  
❌ Coupling to third-party code  
❌ Refactoring your own system (very bad)

---

# ✔ 3) Solution — Adapter Pattern

Create a class that:
- takes external API format
- converts it to internal format
- exposes a **PayX-friendly interface**

---

# 🎯 4) Real FinTech Usage — 100% real

Banks and payment gateways NEVER have the same API format.

Examples:
✔ PayX internal standardized API  
✔ NPCI UPI API  
✔ Visa/Mastercard API  
✔ Another bank NEFT/RTGS API  
✔ International SWIFT transfer API  
✔ Wallet vendor API  
✔ KYC vendor API

Every external service needs **Adapter**.
**Without Adapter, PayX CANNOT integrate external systems.**

---

# 🏛️ 5) How Spring / Spring Boot uses Adapter Pattern?

Spring uses Adapter everywhere.

### ✔ HandlerAdapter

Matches different controller method signatures.
`@RequestMapping @GetMapping @PostMapping`
Spring uses an internal **HandlerAdapter** to convert HTTP requests into controller method calls.

---

### ✔ HttpMessageConverter

Converts:

- JSON → Java object
- XML → Java object
- Multipart → Java object

These converters are **Adapters**.

---

### ✔ EnvironmentPropertySource
Converts YAML, Properties, System variables into property map.

---

### ✔ WebFlux → Reactive Adapter
Converts blocking servlet API → reactive event-loop API.

---

### ✔ Database Adapter
JPA → Hibernate  
JDBC → DataSource  
MongoTemplate  
RedisTemplate

---

# 🧩 6) PayX Implementation (Adapter)

## Scenario:

PayX has its own internal “Fund Transfer API”:

`transfer(amount, fromAcc, toAcc)`

But external BankSystem API is:

`sendFunds(src, dest, amtInPaise);`

We must adapt the external API to PayX API.

---

# ❌ BAD IMPLEMENTATION (No adapter)

`ExternalBankApi api = new ExternalBankApi(); api.sendFunds(fromAcc, toAcc, amount * 100);`

Problems:

- tightly coupled to ExternalBankApi
- if external API changes → entire system breaks
- cannot replace vendor easily
- cannot write unified logic
- no abstraction


---

# ✔ GOOD DESIGN (Adapter Pattern)

We define:

### Step 1 — PayX interface (what OUR system expects)

`public interface PayXBankAPI
{     
boolean transfer(double amount, String fromAcc, String toAcc);
}`

---

### Step 2 — External bank API (incompatible)

`public class ExternalBankAPI
{     
public boolean sendFunds(String src, String dest, int amtInPaise)
{         System.out.println("External Bank: Sending " + amtInPaise + " paise");         
return true;    
}
}`

---

### Step 3 — Adapter for external bank

`public class ExternalBankAdapter implements PayXBankAPI
{      
private final ExternalBankAPI externalAPI = new ExternalBankAPI();      
@Override     
public boolean transfer(double amount, String fromAcc, String toAcc)
{          
int paise = (int)(amount * 100);  // PayX uses rupees         
System.out.println("Adapter converting amount: " + amount + " to paise: " + paise);          return externalAPI.sendFunds(fromAcc, toAcc, paise);     
}
}`

---

### Step 4 — PayX uses the adapter

```
public class PayXFundTransferService {

    private final PayXBankAPI bankAPI;

    public PayXFundTransferService(PayXBankAPI bankAPI) {
        this.bankAPI = bankAPI;
    }

    public void executeTransfer(double amt, String from, String to) {
        boolean success = bankAPI.transfer(amt, from, to);
        if (success) {
            System.out.println("PayX Transfer Completed Successfully");
        }
    }
}
```

---

### Step 5 — Usage in Main

```
public class Main {
    public static void main(String[] args) {

        PayXBankAPI api = new ExternalBankAdapter();
        PayXFundTransferService service = new PayXFundTransferService(api);

        service.executeTransfer(500.0, "ACC123", "ACC999");
    }
}
```
---

# 🧠 Final Flow (Very Important)

```
PayXFundTransferService
           ↓  expects PayX API
ExternalBankAdapter  ← Converts format
           ↓
ExternalBankAPI      ← Third-party system

```

So PayX NEVER touches third-party code.

---

# 🔥 Why Adapter is IMPORTANT

✔ External API integration  
✔ Vendor independence  
✔ Replace vendor without code changes  
✔ Clean abstraction  
✔ Maintain internal unified PayX API  
✔ Avoid rewriting functionality

---

# 🏛️ Spring uses Adapter constantly

### When you send JSON:

`{"name":"Adhish"}`

Spring converts → Java object using **MessageConverter Adapter**.

### When you handle @GetMapping:

Spring uses **HandlerAdapter** to invoke your controller method.

### When using RestTemplate:

Spring uses **ClientHttpRequestFactoryAdapter**.

### DataSource:

Spring wraps actual JDBC driver with **DataSourceTransactionManagerAdapter**.