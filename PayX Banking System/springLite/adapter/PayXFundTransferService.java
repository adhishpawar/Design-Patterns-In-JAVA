package adapter;

import jdk.jfr.TransitionFrom;

public class PayXFundTransferService {

    private final PayXBankAPI bankAPI;

    public PayXFundTransferService(PayXBankAPI bankAPI)
    {
        this.bankAPI = bankAPI;
    }

    public void transfer(double amount, String fromAcc, String toAcc){
        System.out.println("payX Initiating Fund transfer");

        boolean ok = bankAPI.transfer(amount, fromAcc, toAcc);

        if(ok)
            System.out.println("PayX Transfer Success for  " + fromAcc + "to" + toAcc);
        else
            System.out.println("Transfer Failed");
    }
}
