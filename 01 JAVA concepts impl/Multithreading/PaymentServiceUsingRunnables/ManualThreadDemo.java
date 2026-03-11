public class ManualThreadDemo {

    public static void main(String[] args) {

        PaymentTask task1 = new PaymentTask("TXN-101");
        PaymentTask task2 = new PaymentTask("TXN-102");

        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task2);

        t1.start();
        t2.start();
    }
}
