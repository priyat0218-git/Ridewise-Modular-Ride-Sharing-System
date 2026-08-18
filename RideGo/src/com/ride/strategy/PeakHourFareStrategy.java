package com.ride.strategy;
/**
 * Purpose:
 * Calculates fare during peak/busy hours.
 *
 * It first calculates normal fare and then
 * applies a surge multiplier.
 */
import com.ride.model.Ride;

public class PeakHourFareStrategy implements FareStrategy {

    @Override
    public double calculateFare(Ride ride) {
        // Normal fixed fare
        double baseFare = 50;

        // Normal per-km charge
        double perKmCharge = 10;

        // During peak time, fare is 1.5 times normal fare
        double surgeMultiplier = 1.5;

        // Calculate normal fare first
        double normalFare =
                baseFare +
                        ride.getDistance() * perKmCharge;

        // Apply peak-hour multiplier
        return normalFare * surgeMultiplier;
    }


}
