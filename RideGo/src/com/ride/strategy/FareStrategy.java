package com.ride.strategy;
/**
 * Purpose:
 * Defines HOW fare should be calculated.
 *
 * We can create multiple fare algorithms
 * without changing RideService.
 */
import com.ride.model.Ride;

public interface FareStrategy {
    /**
     * Calculates fare for the given ride.
     */
    double calculateFare(Ride ride);

}

