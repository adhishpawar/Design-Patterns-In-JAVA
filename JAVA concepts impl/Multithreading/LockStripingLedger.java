import java.util.concurrent.locks.ReentrantLock;

class LockStripingLedger {
    private int balance = 0;
    private final ReentrantLock lock = new ReentrantLock();

    public void credit(int amount) {
        lock.lock();
        try {
            balance += amount;
        } finally {
            lock.unlock();
        }
    }

    public void debit(int amount) {
        lock.lock();
        try {
            balance -= amount;
        } finally {
            lock.unlock();
        }
    }

    public int getBalance() {
        lock.lock();
        try {
            return balance;
        } finally {
            lock.unlock();
        }
    }
}
