import java.util.*;

/**
 * SERVICE - CancellationService
 * Handles LIFO rollback logic using a Stack.
 */
class CancellationService {
    // Stack tracks released room IDs (Last-In-First-Out)
    private Stack<String> releasedRoomIDs = new Stack<>();
    private Map<String, Integer> inventory;

    public CancellationService(Map<String, Integer> inventory) {
        this.inventory = inventory;
    }

    public void cancelBooking(String resId, String roomType, String roomId) {
        System.out.println("Processing Cancellation for: " + resId);

        // 1. Rollback: Add room ID back to the stack
        releasedRoomIDs.push(roomId);

        // 2. Inventory Restoration: Increment count
        inventory.put(roomType, inventory.get(roomType) + 1);

        System.out.println("SUCCESS: Room " + roomId + " returned to pool. " + roomType + " inventory incremented.");
    }

    public void displayRollbackStatus() {
        System.out.println("Recent Rooms returned to pool: " + releasedRoomIDs);
    }
}

public class BookMyStayApp {
    public static void main(String[] args) {
        System.out.println("--- BookMyStayApp: Version 10.0 (Cancellation & Rollback) ---\n");

        // Initial State
        Map<String, Integer> inventory = new HashMap<>();
        inventory.put("Single", 4);

        CancellationService cancelService = new CancellationService(inventory);

        // Simulate a Cancellation Request
        String reservationToCancel = "RES-999";
        String roomType = "Single";
        String roomID = "RM-101";

        System.out.println("Initial Inventory: " + inventory);

        // Execute Rollback
        cancelService.cancelBooking(reservationToCancel, roomType, roomID);

        // Verify System State
        System.out.println("Updated Inventory: " + inventory);
        cancelService.displayRollbackStatus();
    }
}