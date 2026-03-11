package facade.dto;

public class FundTransferRequest {

    public final String fromAccount;
    public final String toAccount;
    public final double amount;
    public final String remarks;
    public final String channel;
    public final String deviceId;

    private FundTransferRequest(Builder builder) {
        this.fromAccount = builder.fromAccount;
        this.toAccount = builder.toAccount;
        this.amount = builder.amount;
        this.remarks = builder.remarks;
        this.channel = builder.channel;
        this.deviceId = builder.deviceId;
    }

    public static class Builder {

        private String fromAccount;
        private String toAccount;
        private double amount;

        // Optional fields
        private String remarks = "PAYX_TRANSFER";
        private String channel = "MOBILE_APP";
        private String deviceId = "UNKNOWN_DEVICE";

        public Builder fromAccount(String from) {
            this.fromAccount = from;
            return this;
        }

        public Builder toAccount(String to) {
            this.toAccount = to;
            return this;
        }

        public Builder amount(double amt) {
            this.amount = amt;
            return this;
        }

        public Builder remarks(String r) {
            this.remarks = r;
            return this;
        }

        public Builder channel(String ch) {
            this.channel = ch;
            return this;
        }

        public Builder deviceId(String id) {
            this.deviceId = id;
            return this;
        }

        public FundTransferRequest build() {
            return new FundTransferRequest(this);
        }
    }
}
