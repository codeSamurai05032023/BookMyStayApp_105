import java.util.LinkedList;
import java.util.Queue;

/**
 * DOMAIN MODEL - Reservation
 * Represents a guest's intent to book a room.
 */
class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() { return guestName; }
    public String getRoomType() { return roomType; }

    @Override
    public String toString() {
        return "Reservation [Guest: " + guestName + ", Room Type: " + roomType + " Room]";
    }
}

/**
 * SERVICE - BookingRequestQueue
 * Manages incoming requests using a FIFO Queue.
 */
class BookingRequestQueue {
    // Using LinkedList as the implementation for the Queue interface
    private Queue<Reservation> queue;

    public BookingRequestQueue() {
        this.queue = new LinkedList<>();
    }

    /**
     * Accepts a booking request and adds it to the back of the line.
     */
    public void addRequest(Reservation reservation) {
        queue.offer(reservation); // .offer() safely adds to the tail of the queue
        System.out.println("Request Received -> " + reservation.getGuestName() + " wants a " + reservation.getRoomType() + " Room.");
    }

    /**
     * Displays all pending requests in exact arrival order.
     */
    public void displayPendingRequests() {
        System.out.println("\n--- Pending Booking Requests (FIFO Order) ---");
        if (queue.isEmpty()) {
            System.out.println("The queue is currently empty.");
        } else {
            for (Reservation res : queue) {
                System.out.println(res.toString());
            }
        }
    }
}

/**
 * MAIN CLASS - BookMyStayApp
 * Application Entry Point
 */
public class BookMyStayApp {
    public static void main(String[] args) {
        System.out.println("System Initialized. Accepting incoming traffic...\n");

        // 1. Initialize the Queue Service
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        // 2. Simulate simultaneous booking requests arriving
        bookingQueue.addRequest(new Reservation("Alice", "Suite"));
        bookingQueue.addRequest(new Reservation("Bob", "Single"));
        bookingQueue.addRequest(new Reservation("Charlie", "Double"));
        bookingQueue.addRequest(new Reservation("Diana", "Single"));

        // 3. Display the queue to verify FIFO ordering
        bookingQueue.displayPendingRequests();

        System.out.println("\n[Note: Requests are queued. No inventory has been mutated yet.]");
    }
}