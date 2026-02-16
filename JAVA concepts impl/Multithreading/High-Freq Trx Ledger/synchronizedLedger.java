class synchronizedLedger {
    private int balance = 0;

    public synchronized void credit(int amount) {
        balance += amount;
    }

    public synchronized void debit(int amount) {
        balance -= amount;
    }

    public synchronized int getBalance() {
        return balance;
    }
}
