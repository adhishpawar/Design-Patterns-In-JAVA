package validation;

public interface ValidationHandler {
    void setNext(ValidationHandler next);
    boolean handle(double amount, String accountId, String method);
}
