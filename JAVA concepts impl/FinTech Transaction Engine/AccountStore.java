import java.util.concurrent.ConcurrentHashMap;

public class AccountStore
{
    private final ConcurrentHashMap<Long, Account> accounts = new ConcurrentHashMap<>();

    public Account getOrCreate(long id)
    {
        return accounts.computeIfAbsent(id, k -> new Account(k, 10_000));
    }
}