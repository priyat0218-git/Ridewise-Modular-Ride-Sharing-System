package com.ride.service;
/**
 * Purpose:
 * Responsible for operations related to drivers.
 *
 * It manages:
 * - Registering drivers
 * - Driver availability
 * - Getting available drivers
 */
import com.ride.model.Driver;
import java.util.ArrayList;
import java.util.List;

public class DriverService {
    // Stores all registered drivers
    private final List<Driver> drivers = new ArrayList<>();

    /**
     * Registers a new driver.
     */
    public void registerDriver(Driver driver) {
        drivers.add(driver);
    }
    /**
     * Changes whether a driver is available.
     */
    public void updateAvailability(int driverId, boolean available) {

        for (Driver driver : drivers) {

            if (driver.getId() == driverId) {
                driver.setAvailable(available);
                return;
            }
        }
    }
    /**
     * Returns only drivers who are currently free.
     */
    public List<Driver> getAvailableDrivers() {

        List<Driver> availableDrivers = new ArrayList<>();

        for (Driver driver : drivers) {

            if (driver.isAvailable()) {
                availableDrivers.add(driver);
            }
        }

        return availableDrivers;
    }
    /**
     * Returns all drivers.
     */
    public List<Driver> getAllDrivers() {
        return drivers;
    }
}
