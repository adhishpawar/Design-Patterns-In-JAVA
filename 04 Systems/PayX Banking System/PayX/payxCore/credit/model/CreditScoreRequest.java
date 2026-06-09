package payxCore.credit.model;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CreditScoreRequest {

    private final String userId;
    private final List<Transaction> transactions;
    private final double monthlyIncome;
    private final double monthlyExpense;
    private final String modelVersion;  //for now v1

    public CreditScoreRequest(Builder b){
        this.userId = b.userId;
        this.transactions = Collections.unmodifiableList(b.transactions);
        this.monthlyIncome = b.monthlyIncome;
        this.monthlyExpense = b.monthlyExpense;
        this.modelVersion = b.modelVersion;
    }

    public static class Builder {
        private String userId;
        private List<Transaction> transactions = new ArrayList<>();
        private double monthlyIncome;
        private double monthlyExpense;
        private final String modelVersion = "V1";

        public Builder userId(String id) {
            this.userId = id;
            return this;
        }
        public Builder transactions(List<Transaction> t) {
            this.transactions = t;
            return this;
        }

        public Builder monthlyIncome(double v) {
            this.monthlyIncome = v;
            return this;
        }

        public Builder monthlyExpense(double v) {
            this.monthlyExpense = v;
            return this;
        }

        public CreditScoreRequest build() {
           return new CreditScoreRequest(this);
        }
    }

    public String getUserId() {
        return userId;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public double getMonthlyIncome() {
        return monthlyIncome;
    }

    public double getMonthlyExpense() {
        return monthlyExpense;
    }

    public String getModelVersion() {
        return modelVersion;
    }
}
