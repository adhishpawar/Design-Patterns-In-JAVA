public class BrokenLedgerTest
{
    public static void main(String[] args) throws InterruptedException
    {
        Ledger ledger = new Ledger();


        Runnable task = () -> {
            for(int i=0; i<10000; i++)
            {
                ledger.credit(1);
                ledger.debit(1);
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        Thread t3 = new Thread(task);
        Thread t4 = new Thread(task);
        Thread t5 = new Thread(task);
        Thread t6 = new Thread(task);

        t1.start(); t2.start(); t3.start();
        t6.start(); t4.start(); t5.start();
        t1.join(); t2.join(); t3.join();
        t4.join(); t5.join(); t6.join();

        System.out.println("Final Balance: " + ledger.getBalance());
    }
}
class Ledger
{
    private int balance = 0;

    public void credit(int amount)
    {
        balance += amount;
    }

    public void debit(int amount)
    {
        balance -= amount;
    }

    public int getBalance()
    {
        return balance;
    }
}