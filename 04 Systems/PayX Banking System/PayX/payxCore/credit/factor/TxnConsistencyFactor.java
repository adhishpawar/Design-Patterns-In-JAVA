package payxCore.credit.factor;

import payxCore.credit.model.CreditScoreRequest;
import payxCore.credit.model.Transaction;

import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class TxnConsistencyFactor implements ScoreFactor{


    public String getName() {
        return "TxnConsistency";
    }

    public double getWeight() {
        return 0.45;
    }
    
    public int compute(CreditScoreRequest request) {
        List<Transaction> txns = request.getTransactions();
        if(txns.size() < 5) return 0;

        List<Long> gaps = computeGaps(txns);
        double mean = gaps.stream().mapToLong(Long::longValue).average().orElse(0);
        double stdDev = stdDev(gaps, mean);

        double rawScore = mean == 0 ? 0 : 100 - (stdDev / mean) * 60;
        return (int) Math.max(0, Math.min(100, rawScore));
    }

    private List<Long> computeGaps(List<Transaction> txns) {
        if(txns == null || txns.size() < 2)
            return Collections.emptyList();

        //Sort the Transactions by date
        List<Transaction> sorted = txns.stream()
                .sorted(Comparator.comparing(Transaction::getDate))
                .collect(Collectors.toList());

        //Calculate gaps in days
        List<Long> gaps = new ArrayList<>();

        for(int i=1; i<sorted.size(); i++)
        {
            long gap = ChronoUnit.DAYS.between(
                    sorted.get(i-1).getDate(),
                    sorted.get(i).getDate()

            );

            gaps.add(gap);
        }

        return gaps;
    }

    private double stdDev(List<Long> gaps, double mean) {
        if(gaps == null || gaps.isEmpty())
        {
            return 0.0;
        }

        double sumSquaredDiffs = 0.0;

        for(Long gap : gaps)
        {
            double diff = gap - mean;
            sumSquaredDiffs += diff * diff;
        }

        double variance = sumSquaredDiffs / gaps.size();

        return Math.sqrt(variance);
    }
}
