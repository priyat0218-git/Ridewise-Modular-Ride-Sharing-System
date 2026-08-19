# 🚕  Ridewise-Modular-Ride-Sharing-System
The project demonstrates **Object-Oriented Programming, SOLID principles, Layered Architecture, Strategy Design Pattern, Dependency Injection, Composition over Inheritance, and Custom Exception Handling**.
A simple console-based ride booking system built using **Java**.

## 📌 Project Overview
RideMatch is a simplified ride-booking application where users can:

- Register riders
- Register drivers
- View available drivers
- Request a ride
- Automatically assign a driver
- Calculate ride fare
- Complete a ride
- Cancel a ride
- View ride details

The system is designed to be easily extendable.

For example:

- A new driver-matching algorithm can be added without modifying `RideService`.
- A new fare calculation algorithm can be added without modifying `RideService`.

---

## 🎯 Functional Requirements
      The application supports the following functionality:

        1. Register Rider
        
        A rider can be registered with:
        
        - Rider ID
        - Name
        - Location
        
        2. Register Driver
        
        A driver can be registered with:
        
        - Driver ID
        - Name
        - Current Location
        - Vehicle Type
        - Availability
        
        Supported vehicle types:
        
        - BIKE
        - AUTO
        - CAR
        
        3. View Available Drivers
        
        The system displays all drivers who are currently available.
        
         4. Request Ride
        
        A rider can request a ride by providing:
        
        - Rider ID
        - Distance
        
        The system:
        
        1. Finds the rider.
        2. Gets available drivers.
        3. Applies the configured driver-matching strategy.
        4. Assigns a driver.
        5. Creates the ride.
        6. Changes ride status to `ASSIGNED`.
        
        5. Driver Matching
        
        The system supports multiple driver-matching strategies:
        
        - Nearest Driver
        - Least Active Driver
        
        6. Fare Calculation
        
        The system supports multiple fare strategies:
        
        - Default Fare
        - Peak Hour Fare
        
         7. Complete Ride
        
        When a ride is completed:
        
        - Ride status becomes `COMPLETED`.
        - Driver becomes available again.
        - Driver's active ride count is updated.
        - Fare is calculated.
        - Fare receipt is generated.
        
        8. Cancel Ride
        
        A ride can be cancelled if it has not already been completed.
        
        9. Ride Status
        
        A ride can have the following statuses:
        
        ```text
        REQUESTED
        ASSIGNED
        COMPLETED
        CANCELLED

## 🏗️ Architecture
      The project follows a Layered Architecture.

                 ┌─────────────────────┐
                 │      Main.java      │
                 │  Presentation Layer │
                 └──────────┬──────────┘
                            │
                            ▼
                 ┌─────────────────────┐
                 │    Service Layer    │
                 │                     │
                 │ RiderService        │
                 │ DriverService       │
                 │ RideService         │
                 └──────────┬──────────┘
                            │
                ┌───────────┴───────────┐
                ▼                       ▼
       ┌─────────────────┐     ┌─────────────────┐
       │ Strategy Layer  │     │   Model Layer   │
       │                 │     │                 │
       │ Fare Strategy   │     │ Rider           │
       │ Matching        │     │ Driver          │
       │ Strategy        │     │ Ride            │
       └─────────────────┘     │ Location        │
                               │ FareReceipt     │
                               │ Enums           │
                               └─────────────────┘

## 📂 Project Structure
            src/main/java/com/example/ridematch
      │
      ├── Main.java
      │
      ├── model
      │   ├── Rider.java
      │   ├── Driver.java
      │   ├── Ride.java
      │   ├── Location.java
      │   ├── FareReceipt.java
      │   ├── RideStatus.java
      │   └── VehicleType.java
      │
      ├── service
      │   ├── RiderService.java
      │   ├── DriverService.java
      │   └── RideService.java
      │
      ├── strategy
      │   ├── RideMatchingStrategy.java
      │   ├── NearestDriverStrategy.java
      │   ├── LeastActiveDriverStrategy.java
      │   ├── FareStrategy.java
      │   ├── DefaultFareStrategy.java
      │   └── PeakHourFareStrategy.java
      │
      └── exception
          ├── NoDriverAvailableException.java

## 🎨 Design Patterns Used
   ###  1. Strategy Pattern:-
        Used for two changeable algorithms:

      | Strategy | Implementations |
      |----------|-----------------|
      | Driver Matching | Nearest Driver, Least Active Driver |
      | Fare Calculation | Default Fare, Peak Hour Fare |
      
      This allows new strategies to be added without modifying `RideService`.

   ### 2. Dependency Injection:-
      RideService receives its dependencies through its constructor.dependencies are provided from outside.

  #### Benefits:
      -Loose coupling
      -Easy testing
      -Easy replacement of strategies
      -Better maintainability
      
   ### 3.Composition Over Inheritance:-
      RideService uses strategies through composition.

      RideService
           ├── has-a → RideMatchingStrategy
           └── has-a → FareStrategy
      This is preferable to creating large inheritance hierarchies.

   ### 4.SOLID Principles:-

  #### a) Single Responsibility Principle (SRP)
            Each class has a specific responsibility.
        
                RiderService       → Rider operations
                DriverService      → Driver operations
                RideService        → Ride operations
                FareStrategy       → Fare calculation
                MatchingStrategy   → Driver selection
                
  #### b)  Open/Closed Principle (OCP)
            The system is open for extension but closed for modification.
            For example, a new fare strategy can be added:
                
                public class FestivalFareStrategy implements FareStrategy {
                 // Festival pricing logic
                }
                So that Existing classes don't need to be modified.

### 5. Dependency Inversion Principle (DIP)
    RideService depends on abstractions:
          -RideMatchingStrategy
          -FareStrategy
    instead of depending directly on:
          -NearestDriverStrategy
          -DefaultFareStrategy
    This reduces coupling.

### 6.Exception Handling

The project uses custom runtime exceptions for business errors.

RuntimeException
            ├── NoDriverAvailableException

### 💰 Fare Calculation Flow
         Ride
             │
             ▼
            RideService
             │
             ▼
            FareStrategy
             │
             ├── DefaultFareStrategy
             │
             └── PeakHourFareStrategy
             │
             ▼
            Fare
             │
             ▼
            FareReceipt
### 🔄 Ride Flow
    The basic ride-request flow is:

      User
       │
       ▼
      Main.java
       │
       ▼
      RideService.requestRide()
       │
       ├── Find Rider
       │
       ├── Get Available Drivers
       │
       ├── Check Driver Availability
       │
       ├── Apply Matching Strategy
       │
       ├── Assign Driver
       │
       └── Create Ride
             │
             ▼
          ASSIGNED
    
### 🚗 Driver Matching Flow
       Available Drivers
                   │
                   ▼
            RideMatchingStrategy
                   │
                   ├── NearestDriverStrategy
                   │
                   └── LeastActiveDriverStrategy
                   │
                   ▼
            Selected Driver
                   │
                   ▼
            Ride Assigned

##  🖥️ Console Menu
    The application provides the following menu:

    ===== RIDEMATCH =====
    
    1. Add Rider
    2. Add Driver
    3. View Available Drivers
    4. Request Ride
    5. Complete Ride
    6. Cancel Ride
    7. View Rides
    8. Exit
    
##  🛠️ Technologies Used
    -Java
      -Object-Oriented Programming
      -Java Collections
      -Interfaces
      -Enums
      -Exception Handling
      -SOLID Principles
      -Strategy Design Pattern
      -Dependency Injection
      -Layered Architecture
      
##  ▶️ How to Run
    Prerequisites:
       -Make sure Java is installed.
          -Check using:
                 java -version
          -Recommended:
                Java 17+

   Run from IDE:-
           1. Clone the repository.
           2. Open the project in IntelliJ IDEA / Eclipse.
           3. Locate:
                Main.java
           4. Run Main.java.
##  Key Learning Outcomes
    This project demonstrates:

          -How to design an object-oriented system
          -How to separate responsibilities using layers
          -How to use interfaces for loose coupling
          -How Strategy Pattern makes algorithms replaceable
          -How constructor dependency injection works
          -How to apply SOLID principles
          -How to create custom exceptions
          -How to design maintainable and extensible Java applications
          
##  👩‍💻 Author
    Priyanka Tripathi
    Java Developer | Backend Development | Spring Boot | Microservices

    
                    
