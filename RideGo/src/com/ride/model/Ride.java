package com.ride.model;
/**
 * Purpose:
 * Represents an actual ride/booking.
 *
 * A Ride connects:
 *
 * Rider + Driver + Distance + Status
 */
public class Ride {
    // Unique ID of the ride
    private int id;
    // Customer who requested the ride
    private Rider rider;
    // Driver assigned to the ride
    private Driver driver;
    // Distance that customer wants to travel
    private double distance;
    // Current status of the ride
    private RideStatus  status;

    /**
     * Creates a new ride.
     *
     * Initially the ride is REQUESTED.
     */
    public Ride(int id, Rider rider, double distance) {
        this.id = id;
        this.rider = rider;
        this.distance = distance;
        // At the time of creation,
        // driver has not been assigned yet.
        this.status = RideStatus.REQUESTED;
    }

    public int getId() {
        return id;
    }

    public Rider getRider() {
        return rider;
    }

    public Driver getDriver() {
        return driver;
    }

    public double getDistance() {
        return distance;
    }

    public RideStatus getStatus() {
        return status;
    }
    /**
     * Assigns a driver to the ride.
     */
    public void setDriver(Driver driver) {
        this.driver = driver;
    }
    /**
     * Changes the current status of the ride.
     */
    public void setStatus(RideStatus status) {
        this.status = status;
    }
}
