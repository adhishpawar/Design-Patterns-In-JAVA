package db;

public class DBConnection {

    public DBConnection() {
        System.out.println("❌ New DB Connection created: " + this);
    }

    // fake method to simulate DB usage
    public String getAccountBalance(String accountId) {
        // in future we'll connect to real DB / map
        return "Balance for account " + accountId + " is 1000.00 INR";
    }
}
