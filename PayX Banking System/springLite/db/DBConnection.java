package db;

public class DBConnection {

    public DBConnection() {
        System.out.println("🟢 DBConnection created by BeanFactory");
    }

    public String getAccountBalance(String accId) {
        return "Balance for account " + accId + " is 1000.00 INR";
    }
}


/*We removed:
private constructor
static getInstance()
Because the factory now enforces singleton behavior.*/