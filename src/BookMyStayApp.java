import java.util.*;

/**
 * CUSTOM EXCEPTION - InvalidBookingException
 */
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

/**
 * VALIDATOR - BookingValidator
 * Implements "Fail-Fast" design.
 */
class BookingValidator {
    private static final List<String> VALID_ROOMS = Arrays.asList("Single", "Double", "Suite");

    public void validate(String roomType, int currentAvailability) throws InvalidBookingException {
        // 1. Validate Room Type
        if (!VALID_ROOMS.contains(roomType)) {
            throw new InvalidBookingException("Error: Room type '" + roomType + "' does not exist in our system.");
        }

        // 2. Prevent Negative Inventory (Guarding System State)
        if (currentAvailability <= 0) {
            throw new InvalidBookingException("Error: No " + roomType + " rooms currently available.");
        }
    }
}

public class BookMyStayApp {
    public static void main(String[] args) {
        System.out.println("--- BookMyStayApp: Version 9.0 (Validation & Error Handling) ---\n");

        BookingValidator validator = new BookingValidator();

        // Mocking an inventory state
        Map<String, Integer> inventory = new HashMap<>();
        inventory.put("Single", 1);
        inventory.put("Double", 0); // No availability for Double

        // Test Scenarios
        processRequest(validator, "Deluxe", 0);        // Scenario 1: Invalid Room Type
        processRequest(validator, "Double", 0);        // Scenario 2: Out of Stock
        processRequest(validator, "Single", 1);        // Scenario 3: Valid Request

        System.out.println("\nSystem: Remained stable after all validation checks.");
    }

    private static void processRequest(BookingValidator v, String type, int availability) {
        try {
            System.out.println("Validating request for: " + type + "...");
            v.validate(type, availability);
            System.out.println("SUCCESS: Request is valid. Proceeding to booking.\n");
        } catch (InvalidBookingException e) {
            // Graceful failure handling
            System.err.println("VALIDATION FAILED: " + e.getMessage() + "\n");
        }
    }
}