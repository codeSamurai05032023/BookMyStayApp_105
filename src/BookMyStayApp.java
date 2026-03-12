import java.util.*;

/**
 * SERVICE - ConcurrentBookingProcessor
 * Handles multiple threads competing for the same inventory.
 */
class ConcurrentBookingProcessor {
    private Map<String, Integer> inventory;

    public ConcurrentBookingProcessor(Map<String, Integer> inventory) {
        this.inventory = inventory;
    }

    /**
     * The CRITICAL SECTION
     * 'synchronized' ensures only one thread can execute this at a time.
     */
    public synchronized void bookRoom(String guestName, String roomType) {
        int available = inventory.getOrDefault(roomType, 0);

        System.out.println(guestName + " is checking availability for " + roomType + "...");

        if (available > 0) {
            // Simulate processing time to increase the chance of a race condition
            try { Thread.sleep(100); } catch (InterruptedException e) {}

            inventory.put(roomType, available - 1);
            System.out.println("SUCCESS: " + guestName + " booked a " + roomType + ". Remaining: " + (available - 1));
        } else {
            System.out.println("FAILURE: " + guestName + " found no " + roomType + " rooms left.");
        }
    }
}

public class BookMyStayApp {
    public static void main(String[] args) {
        System.out.println("--- BookMyStayApp: Version 11.0 (Concurrent Bookings) ---\n");

        // Shared Mutable State
        Map<String, Integer> sharedInventory = new HashMap<>();
        sharedInventory.put("Single", 1); // Only ONE room for multiple guests

        ConcurrentBookingProcessor processor = new ConcurrentBookingProcessor(sharedInventory);

        // Simulate 3 guests trying to book the same 1 room simultaneously
        Thread guest1 = new Thread(() -> processor.bookRoom("Guest_Alpha", "Single"));
        Thread guest2 = new Thread(() -> processor.bookRoom("Guest_Beta", "Single"));
        Thread guest3 = new Thread(() -> processor.bookRoom("Guest_Gamma", "Single"));

        guest1.start();
        guest2.start();
        guest3.start();

        // Wait for all threads to finish
        try {
            guest1.join();
            guest2.join();
            guest3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nFinal Inventory State: " + sharedInventory);
    }
}