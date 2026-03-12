import java.util.*;

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
    }
}

/**
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
        }
    }
}

/**
 * MAIN CLASS - BookMyStayApp
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
    }
}