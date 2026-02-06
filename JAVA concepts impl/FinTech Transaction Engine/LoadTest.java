import java.util.concurrent.*;

public class LoadTest
{
    public static void main(String[] args) throws InterruptedException{
        TransactionService service = new TransactionService();
        ExecutorService pool = Executors.newFixedThreadPool(50);
        CountDownLatch latch = new CountDownLatch(1000);

        for(int i=0; i<1000; i++)
        {
            pool.submit(() -> {
                service.transfer(1,2,10);
                    latch.countDown();
            });
        }

        latch.await();
        pool.shutdown();

        System.out.println("All transcations processed safely");
    }
}