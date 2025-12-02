package adapter;

//Makes ExternalBankAPI compatible with PayXBankAPI
public class ExternalBankAdapter implements PayXBankAPI{

    private final ExternalBankAPI externalBankAPI;

    public ExternalBankAdapter(){
        this.externalBankAPI  =  new ExternalBankAPI();
    }

    @Override
    public boolean transfer(double amount, String fromAcc, String toAcc) {

        //Covert rupees to paise

        int paise = (int) (amount * 100);
        System.out.println(" Adapter converting: " + amount + " INR to " + paise + " paise");
        System.out.println(" Adapter mapping PayX params to ExternalBankAPI format...");

        // Delegate call
        return externalBankAPI.sendFunds(fromAcc, toAcc, paise);
    }
}
