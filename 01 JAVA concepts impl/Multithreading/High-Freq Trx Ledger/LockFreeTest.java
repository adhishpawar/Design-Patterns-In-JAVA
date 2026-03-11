public class LockFreeTest
{
    public static void main(String[] args) throws InterruptedException
    {
        LockFreeLedger ledger = new LockFreeLedger();

        Runnable task = () -> {
            for (int i = 0; i < 1_000_000; i++) {
                ledger.credit(1);
                ledger.debit(1);
            }
        };

        Thread[] threads = new Thread[8];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(task);
            threads[i].start();
        }

        for (Thread t : threads) t.join();

        System.out.println("Final Balance: " + ledger.getBalance());
    }
}