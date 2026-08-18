package com.ride.strategy;
/**
 * Purpose:
 * Selects the available driver who currently
 * has the least number of active rides.
 */
import com.ride.model.Driver;
import com.ride.model.Rider;
import java.util.List;

public  class LeastActiveDriverStrategy implements RideMatchingStrategy {
    @Override
    public Driver findDriver(Rider rider, List<Driver> drivers) {
        // Initially no driver is selected
        Driver selectedDriver = null;
        // Start with the maximum possible number
        int minimumActiveRides = Integer.MAX_VALUE;

        for (Driver driver : drivers) {
            // Ignore busy drivers
            if (!driver.isAvailable()) {
                continue;
            }
            // Check whether this driver has
            // fewer active rides
            if (driver.getActiveRides() < minimumActiveRides) {
                minimumActiveRides = driver.getActiveRides();
                selectedDriver = driver;
            }
        }

        return selectedDriver;
    }
}
