//Account (Thread-Safe)
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

public class Account
{
    final long id;
    final AtomicLong balance; //--> fast reads
    final ReentrantLock lock = new ReentrantLock(); //--> multi-account safety

    Account(long id, long initialBalance)
    {
        this.id = id;
        this.balance = new AtomicLong(initialBalance);
    }
}