package com.ride.strategy;
/**
 * Purpose:
 * Calculates fare using normal pricing.
 */
import com.ride.model.Ride;

public class DefaultFareStrategy implements FareStrategy {
    @Override
    public double calculateFare(Ride ride) {
        // Fixed amount charged for every ride
        double baseFare = 50;
        // Amount charged for every kilometre
        double perKmCharge = 10;
        // Total = base fare + distance-based charge
        return baseFare + ride.getDistance() * perKmCharge;
    }
}
