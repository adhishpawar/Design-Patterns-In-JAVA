import facade.PayXFacade;
import facade.dto.PaymentRequest;
import facade.dto.FundTransferRequest;

public class Main {
    public static void main(String[] args) {

        PayXFacade facade = new PayXFacade();

        // ------- Payment using Builder -------
        PaymentRequest payment = new PaymentRequest.Builder()
                .accountId("ACC123")
                .amount(750.00)
                .method("UPI")
                .currency("INR")
                .ipAddress("192.168.0.55")
                .deviceId("PIXEL-9")
                .build();

        facade.processPayment(payment);

        // ------- Fund Transfer using Builder -------
        FundTransferRequest transfer = new FundTransferRequest.Builder()
                .fromAccount("ACC111")
                .toAccount("ACC999")
                .amount(1200.00)
                .remarks("Rent Payment")
                .channel("MOBILE_APP")
                .deviceId("IPHONE-15")
                .build();

        facade.transfer(transfer);
    }
}
