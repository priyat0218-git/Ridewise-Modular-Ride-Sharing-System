package com.ride.exception;
/**
 * Purpose:
 * Thrown when there are no available drivers
 * for a ride request.
 */
public class NoDriverAvailableException extends Throwable {
    public NoDriverAvailableException(String message) {
        super(message);
    }
}
