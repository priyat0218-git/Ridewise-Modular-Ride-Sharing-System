package com.ride.service;
/**
 * Purpose:
 * Responsible for operations related to riders.
 *
 * It does NOT calculate fares.
 * It does NOT find drivers.
 * It only manages riders.
 */
import com.ride.model.Rider;
import java.util.ArrayList;
import java.util.List;

public class RiderService {
    // Stores all registered riders
    private final List<Rider> riders = new ArrayList<>();

    /**
     * Registers a new rider.
     */
    public void registerRider(Rider rider) {
        riders.add(rider);
    }
    /**
     * Finds a rider using rider ID.
     *
     * Returns null if rider doesn't exist.
     */
    public Rider getRiderById(int id) {

        for (Rider rider : riders) {
            if (rider.getId() == id) {
                return rider;
            }
        }

        return null;
    }
}
