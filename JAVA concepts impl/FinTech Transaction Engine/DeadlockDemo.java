import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

public class DeadlockDemo {

    static class Account {
        long id;
        AtomicLong balance = new AtomicLong(10_000);
        ReentrantLock lock = new ReentrantLock();

        Account(long id) {
            this.id = id;
        }
    }

    static Account A = new Account(1);
    static Account B = new Account(2);

    static void transfer(Account from, Account to, long amount) {
        from.lock.lock();
        System.out.println(Thread.currentThread().getName() + " locked FROM " + from.id);

        // Artificial delay to amplify deadlock chance
        sleep(100);

        to.lock.lock();
        System.out.println(Thread.currentThread().getName() + " locked TO " + to.id);

        try {
            from.balance.addAndGet(-amount);
            to.balance.addAndGet(amount);
        } finally {
            to.lock.unlock();
            from.lock.unlock();
        }
    }

    static void safeTransfer(Account from, Account to, long amount) {
        Account first = from.id < to.id ? from : to;
        Account second = from.id < to.id ? to : from;

        first.lock.lock();
        second.lock.lock();
        try {
            from.balance.addAndGet(-amount);
            to.balance.addAndGet(amount);
        } finally {
            second.lock.unlock();
            first.lock.unlock();
        }
    }


    static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {}
    }

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> safeTransfer(A, B, 100), "T1");
        Thread t2 = new Thread(() -> safeTransfer(B, A, 100), "T2");

        t1.start();
        t2.start();
    }
}
