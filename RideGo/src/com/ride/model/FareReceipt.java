package com.ride.model;
/**
 * Purpose:
 * Represents the bill/receipt generated after
 * completing a ride.
 */
import java.time.LocalDateTime;

public class FareReceipt {
    // Ride for which this receipt was generated
    private int rideId;
    // Final amount customer needs to pay
    private double amount;
    // Time when receipt was generated
    private LocalDateTime generatedAt;

    /**
     * Creates a fare receipt.
     */
    public FareReceipt(int rideId, double amount) {
        this.rideId = rideId;
        this.amount = amount;
        // Automatically store current date/time
        this.generatedAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "FareReceipt{" +
                "rideId=" + rideId +
                ", amount=" + amount +
                ", generatedAt=" + generatedAt +
                '}';
    }
}
