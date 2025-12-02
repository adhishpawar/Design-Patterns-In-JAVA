package adapter;

public class ExternalBankAPI {

    public boolean sendFunds(String source, String destination, int amountInPaise){
        System.out.println("External bank: Sending" + amountInPaise +  " paise"  + " from " + source + "to " + destination);
        return true;
    }
}
