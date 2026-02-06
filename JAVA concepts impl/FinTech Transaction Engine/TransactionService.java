public class TransactionService
{
    private final AccountStore store = new AccountStore();

    public boolean transfer(long fromId, long toId, long amount)
    {
        if(fromId == toId)
            return false;

        Account from = store.getOrCreate(fromId);
        Account to = store.getOrCreate(toId);

        Account first = from.id < to.id ? from : to;
        Account second = from.id < to.id ? to : from;

        first.lock.lock();
        second.lock.lock();

        try{
            if(from.balance.get() < amount)
            {
                return false;
            }

            from.balance.addAndGet(-amount);
            to.balance.addAndGet(amount);
            return true;
        }finally {
            second.lock.unlock();
            first.lock.unlock();
        }
    }
}