package facade.dto;

public class PaymentRequest {

    public final String accountId;
    public final double amount;
    public final String method;
    public final String currency;
    public final String ipAddress;
    public final String deviceId;

    private PaymentRequest(Builder builder) {
        this.accountId = builder.accountId;
        this.amount = builder.amount;
        this.method = builder.method;
        this.currency = builder.currency;
        this.ipAddress = builder.ipAddress;
        this.deviceId = builder.deviceId;
    }

    public static class Builder {

        private String accountId;
        private double amount;
        private String method;

        // Optional fields – defaults
        private String currency = "INR";
        private String ipAddress = "0.0.0.0";
        private String deviceId = "UNKNOWN";

        public Builder accountId(String id) {
            this.accountId = id;
            return this;
        }

        public Builder amount(double amt) {
            this.amount = amt;
            return this;
        }

        public Builder method(String m) {
            this.method = m;
            return this;
        }

        public Builder currency(String c) {
            this.currency = c;
            return this;
        }

        public Builder ipAddress(String ip) {
            this.ipAddress = ip;
            return this;
        }

        public Builder deviceId(String did) {
            this.deviceId = did;
            return this;
        }

        public PaymentRequest build() {
            return new PaymentRequest(this);
        }
    }
}
