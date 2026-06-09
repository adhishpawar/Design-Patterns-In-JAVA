package org.example.strategy;

public class HourlyPricing implements PricingStrategy{
    @Override
    public double calculate(long hours) {
        return hours * 20;
    }
}
