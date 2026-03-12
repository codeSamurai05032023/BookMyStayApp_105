<<<<<<< HEAD
import java.util.HashMap;
import java.util.Map;
=======
import java.util.*;
>>>>>>> feature/UC7

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
<<<<<<< HEAD
=======
    }
}

class SingleRoom extends Room { public SingleRoom() { super(1, 250, 1500.0); } }
class DoubleRoom extends Room { public DoubleRoom() { super(2, 400, 2500.0); } }
class SuiteRoom extends Room { public SuiteRoom() { super(3, 750, 5000.0); } }

/**
 * DOMAIN MODEL - AddOnService
 * Represents an optional service with a name and a fixed price.
 */
class AddOnService {
    private String serviceName;
    private double price;

    public AddOnService(String serviceName, double price) {
        this.serviceName = serviceName;
        this.price = price;
    }

    public String getServiceName() { return serviceName; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return serviceName + " ($" + price + ")";
>>>>>>> feature/UC7
    }
}

class SingleRoom extends Room { public SingleRoom() { super(1, 250, 1500.0); } }
class DoubleRoom extends Room { public DoubleRoom() { super(2, 400, 2500.0); } }
class SuiteRoom extends Room { public SuiteRoom() { super(3, 750, 5000.0); } }

/**
<<<<<<< HEAD
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
=======
 * SERVICE MANAGER - AddOnServiceManager
 * Manages the One-to-Many relationship between Reservation IDs and Services.
 */
class AddOnServiceManager {
    // Mapping Reservation ID -> List of selected services
    private Map<String, List<AddOnService>> reservationServices;

    public AddOnServiceManager() {
        this.reservationServices = new HashMap<>();
    }

    /**
     * Adds a service to a specific reservation.
     */
    public void addServiceToReservation(String reservationId, AddOnService service) {
        reservationServices.computeIfAbsent(reservationId, k -> new ArrayList<>()).add(service);
    }

    /**
     * Calculates the total cost of all add-ons for a specific reservation.
     */
    public double calculateTotalServiceCost(String reservationId) {
        List<AddOnService> services = reservationServices.get(reservationId);
        if (services == null) return 0.0;

        double total = 0;
        for (AddOnService s : services) {
            total += s.getPrice();
        }
        return total;
    }

    /**
     * Displays all services attached to a reservation.
     */
    public void displayServices(String reservationId) {
        List<AddOnService> services = reservationServices.get(reservationId);
        if (services == null || services.isEmpty()) {
            System.out.println("No add-on services selected for Reservation: " + reservationId);
        } else {
            System.out.println("Services for " + reservationId + ": " + services);
>>>>>>> feature/UC7
        }
    }
}

/**
 * MAIN CLASS - BookMyStayApp
<<<<<<< HEAD
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
=======
 * Version 7.0: Add-On Service Selection & Cost Aggregation
 * @author CodeSamurai05032023
 * @version 7.0
 */
public class BookMyStayApp {
    public static void main(String[] args) {
        System.out.println("--- BookMyStayApp: Version 7.0 (Add-On Services) ---\n");

        // 1. Initialize the Service Manager
        AddOnServiceManager serviceManager = new AddOnServiceManager();

        // 2. Define available services
        AddOnService wifi = new AddOnService("Premium WiFi", 15.0);
        AddOnService breakfast = new AddOnService("Buffet Breakfast", 25.0);
        AddOnService spa = new AddOnService("Spa Treatment", 100.0);

        // 3. Simulate existing Reservation IDs
        String resId1 = "RES-1001";
        String resId2 = "RES-1002";

        System.out.println("Adding services to " + resId1 + "...");
        serviceManager.addServiceToReservation(resId1, wifi);
        serviceManager.addServiceToReservation(resId1, breakfast);

        System.out.println("Adding services to " + resId2 + "...");
        serviceManager.addServiceToReservation(resId2, spa);

        System.out.println("\n--- Final Summary ---");

        // Display results for Reservation 1
        serviceManager.displayServices(resId1);
        System.out.println("Total Add-On Cost for " + resId1 + ": $" + serviceManager.calculateTotalServiceCost(resId1));

        System.out.println();

        // Display results for Reservation 2
        serviceManager.displayServices(resId2);
        System.out.println("Total Add-On Cost for " + resId2 + ": $" + serviceManager.calculateTotalServiceCost(resId2));

        System.out.println("\nCore booking and inventory logic remains untouched.");
>>>>>>> feature/UC7
    }
}