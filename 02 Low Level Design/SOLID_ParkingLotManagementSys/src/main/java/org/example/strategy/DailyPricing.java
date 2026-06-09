package org.example.strategy;

public class DailyPricing implements PricingStrategy{
    @Override
    public double calculate(long hours) {
        return (hours/24.0) * 300;
    }
}
