package payxCore.credit.factor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FactorRegistry {
    private final List<ScoreFactor> factors = new ArrayList<>();

    public void register(ScoreFactor factor)
    {
        double totalWeight = factors.stream().mapToDouble(ScoreFactor::getWeight).sum() + factor.getWeight();

        if(totalWeight > 1.0001)
        {
            throw new IllegalArgumentException("factor weights exceed 1.0 " + totalWeight);
        }
        factors.add(factor);
    }

    public List<ScoreFactor> getAll(){
        return Collections.unmodifiableList(factors);
    }
}

