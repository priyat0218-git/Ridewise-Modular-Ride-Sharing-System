package com.ride.model;
/**
 * Purpose:
 * Represents a customer who wants to book a ride.
 *
 * Rider is a MODEL class.
 * It mainly stores rider information.
 */
public class Rider {
    // Unique ID of the rider
    private int id;
    // Name of the rider
    private String name;
    // Current location of the rider
    private Location location;

    /*
    * Creates a new rider.
     */
    public Rider(int id, Location location, String name) {
        this.id = id;
        this.location = location;
        this.name = name;
    }


    public int getId() {
        return id;
    }
    public Location getLocation() {
        return location;
    }
    public String getName() {
        return name;
    }

}
