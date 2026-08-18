package com.ride.model;
/**
 * Purpose:
 * Represents the location of a Rider or Driver.
 *
 * we are using x and y coordinates to represent real latitude/longitude.
 */
public class Location {
    private double x;
    private double y;
    /**
     * Creates a location using x and y coordinates.
     */
    public Location(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * Calculates the distance between this location
     * and another location.
     *
     * This is used by NearestDriverStrategy to
     * find the closest driver.
     */
    public double distanceFrom(Location other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;

        return Math.sqrt(dx * dx + dy * dy);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
