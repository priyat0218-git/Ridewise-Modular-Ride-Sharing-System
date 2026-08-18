/**
 * Purpose:
 * Entry point of the application.
 *
 * Main is responsible for:
 * - Showing menu
 * - Taking user input
 * - Calling services
 * - Displaying output
 *
 * Main should NOT contain business logic.
 */
import com.ride.exception.NoDriverAvailableException;
import com.ride.model.Location;
import com.ride.model.Rider;
import com.ride.model.*;
import com.ride.service.DriverService;
import com.ride.service.RideService;
import com.ride.service.RiderService;
import com.ride.strategy.DefaultFareStrategy;
import com.ride.strategy.FareStrategy;
import com.ride.strategy.NearestDriverStrategy;
import com.ride.strategy.RideMatchingStrategy;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    Scanner scanner = new Scanner(System.in);

    // Create services.
    RiderService riderService = new RiderService();
    DriverService driverService = new DriverService();

    // Select which driver-matching algorithm the application should use.
    RideMatchingStrategy matchingStrategy = new NearestDriverStrategy();

    // Select which fare algorithm the application should use.
    FareStrategy fareStrategy = new DefaultFareStrategy();

    // Inject all dependencies into RideService.
    RideService rideService =
            new RideService(
                    riderService,
                    driverService,
                    matchingStrategy,
                    fareStrategy
            );

    while (true) {

        System.out.println("\n===== RIDE BOOKING =====");
        System.out.println("1. Add Rider");
        System.out.println("2. Add Driver");
        System.out.println("3. View Available Drivers");
        System.out.println("4. Request Ride");
        System.out.println("5. Complete Ride");
        System.out.println("6. View Rides");
        System.out.println("7. Exit");

        try {

            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    // Main collects input.RiderService performs the actual operation.
                    addRider(scanner, riderService);
                    break;

                case 2:
                    addDriver(scanner, driverService);
                    break;

                case 3:
                    viewAvailableDrivers(driverService);
                    break;

                case 4:
                    requestRide(scanner, rideService);
                    break;

                case 5:
                    completeRide(scanner, rideService);
                    break;

                case 6:
                    viewRides(rideService);
                    break;

                case 7:
                    System.out.println("Goodbye!");
                    return;

                default:
                    System.out.println(
                            "Invalid choice"
                    );
            }

        } catch (Exception e) {
            // Prevent the application from crashing because of invalid user input.
            System.out.println("Error: " + e.getMessage());
            scanner.nextLine();
        }
    }
}

        /**
         * Takes rider details from user
         * and asks RiderService to register the rider.
         */
    private static void addRider(
        Scanner scanner,
        RiderService riderService
    ) {

    System.out.print("Enter rider id: ");
    int id = scanner.nextInt();

    System.out.print("Enter rider name: ");
    String name = scanner.next();

    System.out.print("Enter X location: ");
    double x = scanner.nextDouble();

    System.out.print("Enter Y location: ");
    double y = scanner.nextDouble();

        // Create Rider object.
        Rider rider = new Rider(id,new Location(x, y), name);

        // Delegate registration to service.
        riderService.registerRider(rider);

         System.out.println("Rider registered successfully");
}
        /**
         * Takes driver details and registers driver.
         */
        private static void addDriver(Scanner scanner, DriverService driverService) {

        System.out.print("Enter driver id: ");
        int id = scanner.nextInt();

        System.out.print("Enter driver name: ");
        String name = scanner.next();

        System.out.print("Enter X location: ");
        double x = scanner.nextDouble();

        System.out.print("Enter Y location: ");
        double y = scanner.nextDouble();

        System.out.println("Vehicle: 1.BIKE 2.AUTO 3.CAR");
        int vehicleChoice = scanner.nextInt();

        VehicleType vehicleType = switch (vehicleChoice) {
            case 1 -> VehicleType.BIKE;
            case 2 -> VehicleType.AUTO;
            case 3 -> VehicleType.CAR;
            default -> throw new IllegalArgumentException(
                    "Invalid vehicle type"
            );
        };
        // Create Driver object.
        Driver driver =
                new Driver(
                    id,
                    name,
                    new Location(x, y),
                    vehicleType
            );
            // Delegate registration to DriverService.
             driverService.registerDriver(driver);

             System.out.println("Driver registered successfully");
}
        /**
         * Displays all currently available drivers.
         */
        private static void viewAvailableDrivers(DriverService driverService) {

            for (Driver driver :
                    driverService.getAvailableDrivers()) {

                System.out.println(
                        driver.getId() + " - " +
                                driver.getName()
                );
            }
}
        /**
         * Takes ride request from user and delegates it to RideService.
         */
private static void requestRide(
        Scanner scanner,
        RideService rideService
) {

    System.out.print("Enter rider id: ");
    int riderId = scanner.nextInt();

    System.out.print("Enter distance: ");
    double distance = scanner.nextDouble();
try{
    // RideService handles all business logic.
    Ride ride = rideService.requestRide(riderId, distance);
//----------------


    } catch (NoDriverAvailableException e) {

        System.out.println(
                "Ride Error: " + e.getMessage()
        );
    }

    //-------
    System.out.println("Ride assigned. Ride ID = " + ride.getId());

    System.out.println(
            "Driver = " +
                    ride.getDriver().getName()
    );
}
        /**
         * Completes a ride and displays fare receipt.
         */
private static void completeRide(
        Scanner scanner,
        RideService rideService
) {

    System.out.print("Enter ride id: ");
    int rideId = scanner.nextInt();

    // RideService completes the ride and calculates the fare.
    FareReceipt receipt = rideService.completeRide(rideId);
    System.out.println(receipt);
}
        /**
         * Displays all rides and their current status.
         */
private static void viewRides(
        RideService rideService
) {

    for (Ride ride :
            rideService.getAllRides()) {

        System.out.println(
                "Ride " + ride.getId() +
                        " | Status = " +
                        ride.getStatus() +
                        " | Rider = " +
                        ride.getRider().getName() +
                        " | Driver = " +
                        (ride.getDriver() == null
                                ? "Not Assigned"
                                : ride.getDriver().getName())
        );
    }
}

