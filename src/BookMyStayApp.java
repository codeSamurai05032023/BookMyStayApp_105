import java.util.HashMap;
import java.util.Map;

/**
 * ABSTRACT CLASS - Room
 * Represents the domain model for a hotel room.
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

    public void displayRoomDetails() {
        System.out.print("Beds: " + numberOfBeds + " | Size: " + squareFeet + " sqft | Price: " + pricePerNight);
    }
}

class SingleRoom extends Room { public SingleRoom() { super(1, 250, 1500.0); } }
class DoubleRoom extends Room { public DoubleRoom() { super(2, 400, 2500.0); } }
class SuiteRoom extends Room { public SuiteRoom() { super(3, 750, 5000.0); } }

/**
 * INVENTORY MANAGEMENT - RoomInventory
 * Acts as the "Single Source of Truth" using a HashMap for O(1) lookup.
 */
class RoomInventory {
    // Key: Room Type (String), Value: Count (Integer)
    private Map<String, Integer> inventory;

    public RoomInventory() {
        this.inventory = new HashMap<>();
    }

    /**
     * Registers or updates room counts in the centralized map.
     */
    public void updateInventory(String roomType, int count) {
        inventory.put(roomType, count);
    }

    /**
     * Retrieves current availability for a specific room type.
     */
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    /**
     * Displays the full state of the inventory.
     */
    public void displayInventory() {
        System.out.println("--- Current Inventory Status ---");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " rooms available");
        }
    }
}

/**
 * MAIN CLASS - BookMyStayApp
 * Version 3.1: Centralized Inventory Refactor
 * @author CodeSamurai05032023
 * @version 3.1
 */
public class BookMyStayApp {
    public static void main(String[] args) {
        System.out.println("BookMyStayApp - System Startup [Version 3.1]");
        System.out.println("Refactoring: Moving to Centralized HashMap Inventory\n");

        // 1. Initialize Inventory Component
        RoomInventory hotelInventory = new RoomInventory();

        // 2. Register Room Types (Populating the HashMap)
        hotelInventory.updateInventory("Single", 5);
        hotelInventory.updateInventory("Double", 3);
        hotelInventory.updateInventory("Suite", 2);

        // 3. Initialize Domain Models for display
        Room single = new SingleRoom();
        Room doubleRm = new DoubleRoom();
        Room suite = new SuiteRoom();

        // 4. Display Room Details and cross-reference with Centralized Inventory
        System.out.println("--- Room Specifications ---");

        System.out.print("Type: Single | ");
        single.displayRoomDetails();
        System.out.println(" | Available: " + hotelInventory.getAvailability("Single"));

        System.out.print("Type: Double | ");
        doubleRm.displayRoomDetails();
        System.out.println(" | Available: " + hotelInventory.getAvailability("Double"));

        System.out.print("Type: Suite  | ");
        suite.displayRoomDetails();
        System.out.println(" | Available: " + hotelInventory.getAvailability("Suite"));

        System.out.println();

        // 5. Demonstrate controlled updates (e.g., after a booking)
        System.out.println("Updating Inventory: 1 Single Room Booked...");
        hotelInventory.updateInventory("Single", hotelInventory.getAvailability("Single") - 1);

        // 6. Final State Check
        hotelInventory.displayInventory();
    }
}