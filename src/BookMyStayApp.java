import java.io.*;
import java.util.*;

/**
 * SERVICE - PersistenceService
 * Handles saving and loading system state to/from a file.
 */
class PersistenceService {
    private static final String FILE_NAME = "hotel_state.ser";

    /**
     * SERIALIZATION - Save state to disk
     */
    public void saveState(Map<String, Integer> inventory, List<String> history) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(inventory);
            oos.writeObject(history);
            System.out.println("SYSTEM: State successfully persisted to " + FILE_NAME);
        } catch (IOException e) {
            System.err.println("ERROR: Could not save state: " + e.getMessage());
        }
    }

    /**
     * DESERIALIZATION - Restore state from disk
     */
    @SuppressWarnings("unchecked")
    public void loadState(Map<String, Integer> inventory, List<String> history) {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("SYSTEM: No previous state found. Starting fresh.");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            Map<String, Integer> loadedInventory = (Map<String, Integer>) ois.readObject();
            List<String> loadedHistory = (List<String>) ois.readObject();

            inventory.putAll(loadedInventory);
            history.addAll(loadedHistory);
            System.out.println("SYSTEM: State restored successfully from last session.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("ERROR: System recovery failed. Data may be corrupted.");
        }
    }
}

public class BookMyStayApp {
    public static void main(String[] args) {
        System.out.println("--- BookMyStayApp: Version 12.0 (Persistence & Recovery) ---\n");

        PersistenceService persistence = new PersistenceService();

        // Current System State
        Map<String, Integer> inventory = new HashMap<>();
        List<String> history = new ArrayList<>();

        // 1. ATTEMPT RECOVERY
        persistence.loadState(inventory, history);

        // 2. SIMULATE ACTIVITY (If fresh start)
        if (history.isEmpty()) {
            System.out.println("First run detected. Adding initial data...");
            inventory.put("Suite", 5);
            history.add("Initial System Setup - Suite Inventory set to 5");
        } else {
            System.out.println("Current History Count: " + history.size());
        }

        // 3. SIMULATE SHUTDOWN & PERSISTENCE
        System.out.println("\nShutting down system...");
        persistence.saveState(inventory, history);
    }
}