/**
 * ABSTRACT CLASS - Room
 * Represents a generic hotel room with shared attributes.
 */
abstract class Room {
    protected int numberOfBeds;
    protected int squareFeet;
    protected double pricePerNight;

    public Room(int numberOfBeds, int squareFeet, double pricePerNight) {
        this.numberOfBeds = numberOfBeds;
        this.squareFeet = squareFeet;
        this.pricePerNight = pricePerNight;
    }

    /**
     * Displays common room attributes to the console.
     */
    public void displayRoomDetails() {
        System.out.println("Beds: " + numberOfBeds);
        System.out.println("Size: " + squareFeet + " sqft");
        System.out.println("Price per night: " + pricePerNight);
    }
}

/**
 * Concrete Room Implementations
 */
class SingleRoom extends Room {
    public SingleRoom() { super(1, 250, 1500.0); }
}

class DoubleRoom extends Room {
    public DoubleRoom() { super(2, 400, 2500.0); }
}

class SuiteRoom extends Room {
    public SuiteRoom() { super(3, 750, 5000.0); }
}

/**
 * MAIN CLASS - BookMyStayApp
 * Entry point demonstrating room initialization and availability tracking.
 * * @author CodeSamurai05032023
 * @version 1.1
 */
public class BookMyStayApp {
    /**
     * The main method initializes the domain models and displays system status.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        // Domain Models: Demonstrating Polymorphism
        Room single = new SingleRoom();
        Room doubleRm = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Static Availability Variables (representing current inventory)
        int singleAvailable = 5;
        int doubleAvailable = 3;
        int suiteAvailable = 2;

        System.out.println("--- Hotel Booking System v1.1 ---");
        System.out.println("--- Room Inventory & Details ---\n");

        System.out.println("Single Room:");
        single.displayRoomDetails();
        System.out.println("Available Units: " + singleAvailable);

        System.out.println("\nDouble Room:");
        doubleRm.displayRoomDetails();
        System.out.println("Available Units: " + doubleAvailable);

        System.out.println("\nSuite Room:");
        suite.displayRoomDetails();
        System.out.println("Available Units: " + suiteAvailable);

        System.out.println("\n--- Initialization Complete ---");
    }
}