package com.ride.model;
/**
 * Purpose:
 * Represents a driver who can provide rides.
 *
 * Driver is a MODEL class.
 * It stores driver-related information.
 */
public class Driver {
    // Unique ID of driver
    private int id;
    // Driver's name
    private String name;
    // Current location of driver
    private Location currentLocation;
    // true  -> driver is free
    // false -> driver is currently busy
    private boolean available;

    // Type of vehicle owned by driver
    private VehicleType vehicleType;
    // Number of rides currently handled by driver
    // Used by LeastActiveDriverStrategy
    private int activeRides;

    /**
     * Creates a new driver.
     *
     * By default, a newly registered driver
     * is available.
     */
    public Driver(
            int id,
            String name,
            Location currentLocation,
            VehicleType vehicleType
    ) {
        this.id = id;
        this.name = name;
        this.currentLocation = currentLocation;
        this.vehicleType = vehicleType;
        // Newly registered driver is available
        this.available = true;
        // Initially driver has no active rides
        this.activeRides = 0;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public boolean isAvailable() {
        return available;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }
    /**
     * Changes driver availability.
     *
     * true  -> driver is free
     * false -> driver is busy
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }
    public int getActiveRides() {
        return activeRides;
    }
    /**
     * Called when a new ride is assigned to the driver.
     */
    public void incrementActiveRides() {
        activeRides++;
    }
    /**
     * Called when a ride is completed.
     */
    public void decrementActiveRides() {
        if (activeRides > 0) {
            activeRides--;
        }
    }
}
