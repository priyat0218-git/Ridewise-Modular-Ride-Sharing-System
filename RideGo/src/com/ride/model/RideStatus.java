package com.ride.model;
/**
 * Purpose:
 * Represents the different states of a ride.
 */
public enum RideStatus {
    // Customer has requested a ride
    REQUESTED,
    // Driver has been assigned
    ASSIGNED,
    // Ride has been successfully completed
    COMPLETED,
    // Ride was cancelled
     CANCELLED
}
