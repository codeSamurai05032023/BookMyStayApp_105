import java.util.HashMap;
import java.util.Map;

/**
 * DOMAIN MODELS (From UC2)
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
        System.out.println("Beds: " + numberOfBeds);
        System.out.println("Size: " + squareFeet + " sqft");
        System.out.println("Price per night: " + pricePerNight);
    }
}

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
 * INVENTORY SYSTEM (From UC3)
 */
class RoomInventory {
    private Map<String, Integer> inventoryMap;

    public RoomInventory() {
        inventoryMap = new HashMap<>();
        inventoryMap.put("Single", 5);
        inventoryMap.put("Double", 3);
        inventoryMap.put("Suite", 2);
    }

    // New Method for UC4: Exposes the map for reading
    public Map<String, Integer> getRoomAvailability() {
        return inventoryMap;
    }
}

/**
 * CLASS - RoomSearchService (NEW for UC4)
 * Provides read-only search functionality.
 */
class RoomSearchService {

    public void searchAvailableRooms(
            RoomInventory inventory,
            Room singleRoom,
            Room doubleRoom,
            Room suiteRoom) {

        Map<String, Integer> availability = inventory.getRoomAvailability();

        System.out.println("Room Search\n");

        // Check and display Single Room availability
        if (availability.containsKey("Single") && availability.get("Single") > 0) {
            System.out.println("Single Room:");
            singleRoom.displayRoomDetails();
            System.out.println("Available: " + availability.get("Single") + "\n");
        }

        // Check and display Double Room availability
        if (availability.containsKey("Double") && availability.get("Double") > 0) {
            System.out.println("Double Room:");
            doubleRoom.displayRoomDetails();
            System.out.println("Available: " + availability.get("Double") + "\n");
        }

        // Check and display Suite Room availability
        if (availability.containsKey("Suite") && availability.get("Suite") > 0) {
            System.out.println("Suite Room:");
            suiteRoom.displayRoomDetails();
            System.out.println("Available: " + availability.get("Suite") + "\n");
        }
    }
}

/**
 * MAIN CLASS - BookMyStayApp
 * Application Entry Point
 */
public class BookMyStayApp {
    public static void main(String[] args) {
        // 1. Initialize Inventory
        RoomInventory inventory = new RoomInventory();

        // 2. Initialize Room Models
        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        // 3. Initialize the Service
        RoomSearchService searchService = new RoomSearchService();

        // 4. Execute the Search
        searchService.searchAvailableRooms(inventory, singleRoom, doubleRoom, suiteRoom);
    }
}