package db;

public class DBConnection {

    // single instance
    private static DBConnection instance;

    private  DBConnection() {
        System.out.println("✅ DBConnection created ONCE: " + this);
    }

    // global access point
    public static synchronized DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public String getAccountBalance(String accountId) {
        return "Balance for account " + accountId + " is 1000.00 INR";
    }
}
