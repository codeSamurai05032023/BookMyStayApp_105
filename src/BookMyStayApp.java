import java.util.*;

/**
 * SERVICE - BookingHistoryService
 * Maintains a chronological record of all confirmed bookings.
 */
class BookingHistoryService {
    // List preserves insertion order (chronological tracking)
    private List<String> history = new ArrayList<>();

    public void recordBooking(String resId, String details) {
        String entry = "[" + new java.util.Date() + "] ID: " + resId + " | " + details;
        history.add(entry);
    }

    public void displayHistory() {
        System.out.println("\n--- Administrative Booking Report ---");
        if (history.isEmpty()) {
            System.out.println("No history records found.");
        } else {
            for (String record : history) {
                System.out.println(record);
            }
        }
    }
}

/**
 * MAIN CLASS - BookMyStayApp
 * Version 8.0: Operational Visibility & History Tracking
 */
public class BookMyStayApp {
    public static void main(String[] args) {
        System.out.println("--- BookMyStayApp: Version 8.0 (Booking History) ---\n");

        // 1. Initialize History Service
        BookingHistoryService historyService = new BookingHistoryService();

        // 2. Simulate a series of confirmed bookings (Ordered Storage)
        System.out.println("Processing bookings...");

        // Booking 1
        String id1 = "RES-2001";
        historyService.recordBooking(id1, "Confirmed - Single Room - Paid $1500.0");

        // Booking 2
        String id2 = "RES-2002";
        historyService.recordBooking(id2, "Confirmed - Suite - Paid $5000.0");

        // 3. Admin requests a report (Operational Visibility)
        historyService.displayHistory();

        System.out.println("\nSystem: History preserved in memory for reporting.");
    }
}