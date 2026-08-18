package com.ride.strategy;
/**
 * Purpose:
 * Selects the available driver who is closest
 * to the rider.
 */
import com.ride.model.Driver;
import com.ride.model.Rider;
import java.util.List;

public class NearestDriverStrategy implements RideMatchingStrategy {


    @Override
    public Driver findDriver(Rider rider, List<Driver> drivers) {
        // Initially, we haven't found any driver
        Driver nearestDriver = null;
        // Start with the largest possible distance
        double minimumDistance = Double.MAX_VALUE;
        // Check every driver
        for (Driver driver : drivers) {

            // Ignore drivers who are already busy
            if (!driver.isAvailable()) {
                continue;
            }
            // Calculate distance between rider and driver
            double distance =
                    rider.getLocation()
                            .distanceFrom(driver.getCurrentLocation());
            // If this driver is closer than
            // the previously selected driver,
            // select this driver.
            if (distance < minimumDistance) {
                minimumDistance = distance;
                nearestDriver = driver;
            }
        }
        // Return nearest available driver.
        // Returns null if no driver is available.
        return nearestDriver;
    }
    }


