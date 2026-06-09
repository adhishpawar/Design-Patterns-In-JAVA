package org.example.service;

import org.example.strategy.PricingStrategy;

public class PricingService {

    private PricingStrategy strategy;

    public PricingService(PricingStrategy strategy) {
        this.strategy = strategy;
    }

    public double calculateFee(long hours)
    {
        return strategy.calculate(hours);
    }
}
