import java.util.concurrent.atomic.AtomicInteger;

public class LockFreeLedger
{
    private final AtomicInteger  balance  = new AtomicInteger(0);

    public void credit(int amount)
    {
        balance.addAndGet(amount);
    }

    public void debit(int amount)
    {
        balance.addAndGet(-amount);
    }

    public int getBalance()
    {
        return balance.get();
    }
}