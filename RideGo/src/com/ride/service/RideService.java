package com.ride.service;
/**
 * Purpose:
 * Handles the main ride-related business operations.
 * It coordinates:
 * RiderService
 * DriverService
 * RideMatchingStrategy
 * FareStrategy
 *
 * Notice that it DOES NOT contain the actual
 * matching algorithm or fare algorithm.
 *
 * Those responsibilities are delegated to strategies.
 */
import com.ride.exception.NoDriverAvailableException;
import com.ride.model.FareReceipt;
import com.ride.model.Ride;
import com.ride.model.*;
import com.ride.strategy.FareStrategy;
import com.ride.strategy.RideMatchingStrategy;
import java.util.ArrayList;
import java.util.List;

public class RideService {
    // Used to find rider information
    private final RiderService riderService;
    // Used to find available drivers
    private final DriverService driverService;
    // Decides how a driver should be selected
    private final RideMatchingStrategy matchingStrategy;
    // Decides how fare should be calculated
    private final FareStrategy fareStrategy;

    // Stores all rides
    private final List<Ride> rides = new ArrayList<>();
    // Used to generate unique ride IDs
    private int rideIdCounter = 1;
    /**
     * Constructor injection.
     * We provide the required strategies from outside.
     * This follows Dependency Inversion Principle
     * and makes the class loosely coupled.
     */
    public RideService(
            RiderService riderService,
            DriverService driverService,
            RideMatchingStrategy matchingStrategy,
            FareStrategy fareStrategy
    ) {
        this.riderService = riderService;
        this.driverService = driverService;
        this.matchingStrategy = matchingStrategy;
        this.fareStrategy = fareStrategy;
    }
    /**
     * Main operation for requesting a ride.
     * Steps:
     * 1. Find rider
     * 2. Find available drivers
     * 3. Ask matching strategy to select driver
     * 4. Create ride
     * 5. Assign driver
     * 6. Mark driver unavailable
     */
    public Ride requestRide(int riderId, double distance) throws NoDriverAvailableException {
        // Step 1:
        // Find the rider who requested the ride.
        Rider rider = riderService.getRiderById(riderId);

        if (rider == null) {
            throw new IllegalArgumentException("Rider not found");
        }

        // Step 2:
        // Get drivers who are currently available.
        List<Driver> availableDrivers =
                driverService.getAvailableDrivers();

        if (availableDrivers.isEmpty()) {
            throw new NoDriverAvailableException("No drivers are currently available");
        }

        // Step 3:
        // Create a new ride.Initially its status is REQUESTED.
        Ride ride = new Ride(rideIdCounter++, rider, distance);

        // Step 4:
        // We don't decide which driver to select here.
        // We simply ask the injected strategy.
        Driver driver = matchingStrategy.findDriver(rider, availableDrivers);

        if (driver == null) {
            throw new IllegalStateException("Unable to find driver");
        }
        // Step 5:Assign selected driver to ride.
        ride.setDriver(driver);

        // Driver has now been assigned.
        ride.setStatus(RideStatus.ASSIGNED);

        // Step 6:Driver is no longer available for another ride.
        driver.setAvailable(false);

        // Increase driver's active ride count.
        driver.incrementActiveRides();

        // Store ride in our ride list.
        rides.add(ride);

        return ride;
    }

    /**
     * Completes a ride.
     * Steps:
     * 1. Find ride
     * 2. Validate ride status
     * 3. Mark ride COMPLETED
     * 4. Make driver available again
     * 5. Calculate fare
     * 6. Generate receipt
     */
    public FareReceipt completeRide(int rideId) {

        // Find the ride
        Ride ride = getRideById(rideId);
        if (ride == null) {
            throw new IllegalArgumentException(
                    "Ride not found"
            );
        }

        // Only ASSIGNED rides can be completed.
        if (ride.getStatus() != RideStatus.ASSIGNED) {
            throw new IllegalStateException(
                    "Ride cannot be completed"
            );
        }
        // Mark ride as completed.
        ride.setStatus(RideStatus.COMPLETED);

        // Get the driver who completed the ride.
        Driver driver = ride.getDriver();

        // Driver is free again.
        driver.setAvailable(true);

        // Reduce active ride count.
        driver.decrementActiveRides();

        // Ask FareStrategy to calculate fare.
        double fare = fareStrategy.calculateFare(ride);

        // Generate and return receipt.
        return new FareReceipt(ride.getId(), fare);
    }

    // Returns all rides.
    public List<Ride> getAllRides() {
        return rides;
    }


     // Internal helper method.Finds a ride using its ID.
    private Ride getRideById(int rideId) {

        for (Ride ride : rides) {
            if (ride.getId() == rideId) {
                return ride;
            }
        }

        return null;
    }
}
