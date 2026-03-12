import java.util.HashMap;
import java.util.Map;

/**
 * CLASS - RoomInventory
 * Encapsulates the centralized state of all room availability.
 * @version 3.0
 */
class RoomInventory {
    // The Single Source of Truth for availability
    private Map<String, Integer> inventoryMap;

    public RoomInventory() {
        inventoryMap = new HashMap<>();
        // Initializing the inventory
        inventoryMap.put("Single", 5);
        inventoryMap.put("Double", 3);
        inventoryMap.put("Suite", 2);
    }

    /**
     * Retrieves the current availability for a specific room type.
     */
    public int getAvailableRooms(String roomType) {
        return inventoryMap.getOrDefault(roomType, 0);
    }

    /**
     * Updates the availability in a controlled manner.
     */
    public void updateAvailability(String roomType, int newCount) {
        if (inventoryMap.containsKey(roomType)) {
            inventoryMap.put(roomType, newCount);
        } else {
            System.out.println("Error: Room type '" + roomType + "' does not exist.");
        }
    }

    /**
     * Displays the current state of the entire inventory.
     */
    public void displayInventory() {
        System.out.println("--- Current Room Inventory ---");
        for (Map.Entry<String, Integer> entry : inventoryMap.entrySet()) {
            System.out.println(entry.getKey() + " Room: " + entry.getValue() + " available");
        }
    }
}

/**
 * MAIN CLASS - BookMyStayApp
 * Demonstrates centralized inventory management using a HashMap.
 * @version 3.0
 */
public class BookMyStayApp {
    public static void main(String[] args) {
        // 1. Initialize the centralized inventory
        RoomInventory inventory = new RoomInventory();

        // 2. Display initial state
        inventory.displayInventory();

        // 3. Simulate a booking (Update state)
        System.out.println("\n[System] Booking 1 Single Room...");
        int currentSingle = inventory.getAvailableRooms("Single");
        inventory.updateAvailability("Single", currentSingle - 1);

        System.out.println("[System] Booking 1 Suite Room...");
        int currentSuite = inventory.getAvailableRooms("Suite");
        inventory.updateAvailability("Suite", currentSuite - 1);

        // 4. Display updated state
        System.out.println();
        inventory.displayInventory();
    }
}