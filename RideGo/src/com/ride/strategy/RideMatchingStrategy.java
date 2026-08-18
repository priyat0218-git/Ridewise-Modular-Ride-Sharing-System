package com.ride.strategy;
/**
 * Purpose:
 * Defines HOW a driver should be selected.
 *
 * This is an interface because we may have
 * multiple driver-selection algorithms.
 *
 * Example:
 * 1. Nearest driver
 * 2. Least active driver
 */
import com.ride.model.Driver;
import com.ride.model.Rider;
import java.util.List;
public interface RideMatchingStrategy {
    /**
     * Finds the most suitable driver for the rider.
     *
     * Different implementations can use
     * different algorithms.
     */
    Driver findDriver(Rider rider, List<Driver> drivers);
}
