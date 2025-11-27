package validation;

public abstract class BaseValidationHandler implements ValidationHandler{

    protected ValidationHandler next;

    @Override
    public void setNext(ValidationHandler next){
        this.next = next;
    }

    protected boolean nextHandler(double amount, String accountId, String method){
        if(next == null) return true;
        return next.handle(amount, accountId, method);
    }
}
