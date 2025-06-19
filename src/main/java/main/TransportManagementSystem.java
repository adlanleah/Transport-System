package main;

import user.Student;
import user.Lecturer;
import user.TransportOfficer;
import vehicle.Bus;
import vehicle.Van;
import services.TransportService;
import services.Route;

import java.util.Date;
import java.util.Scanner;

/**
 * Main TransportManagementSystem class
 * Demonstrates integration of all OOP principles and system functionality
 */
public class TransportManagementSystem {

    private final TransportService transportService;
    private final Scanner scanner;

    public TransportManagementSystem() {
        this.transportService = new TransportService();
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        TransportManagementSystem utms = new TransportManagementSystem();
        utms.runSystem();
    }

    public void runSystem() {
        System.out.println("=================================================");
        System.out.println("VICTORIA UNIVERSITY TRANSPORT MANAGEMENT SYSTEM");
        System.out.println("=================================================");
        System.out.println();

        // Initialize system with sample data
        initializeSystemData();

        // Demonstrate OOP principles
        demonstrateOOPPrinciples();

        // Interactive menu
        runInteractiveMenu();
    }

    private void initializeSystemData() {
        System.out.println(" INITIALIZING SYSTEM DATA...\n");

        // Create users demonstrating Inheritance and Polymorphism
        Student student1 = new Student("U001", "Alice Johnson", "alice@vu.edu", "0756123456",
                "pass123", "S2023001", "Computer Science", 2);
        Student student2 = new Student("U002", "Bob Smith", "bob@vu.edu", "0756123457",
                "pass456", "S2023002", "Engineering", 1);

        Lecturer lecturer1 = new Lecturer("U003", "Dr. Sarah Wilson", "sarah@vu.edu", "0756123458",
                "pass789", "L001", "Computer Science", "Senior Lecturer");

        TransportOfficer officer1 = new TransportOfficer("U004", "James Brown", "james@vu.edu", "0756123459",
                "pass321", "T001", "Transport Dept", new String[]{"ASSIGN_VEHICLE", "GENERATE_REPORT"}, 8);

        // Register users
        transportService.registerUser(student1);
        transportService.registerUser(student2);
        transportService.registerUser(lecturer1);
        transportService.registerUser(officer1);

        // Create vehicles
        Bus bus1 = new Bus("B001", "Toyota", 60, "Peter Okello", "UBG123X");
        Van van1 = new Van("V001", "Nissan", 15, "Grace Namatovu", "UBF456Y", "Passenger", false);
        Van van2 = new Van("V002", "Ford", 10, "Samuel Kato", "UBH789Z", "Cargo", true);

        // Add vehicles
        transportService.addBus(bus1);
        transportService.addVan(van1);
        transportService.addVan(van2);

        // Create routes
        Route route1 = new Route("R001", "Main Campus Loop", "Main Gate", "Library", 5.0, 20, "Regular");
        Route route2 = new Route("R002", "City Shuttle", "Main Gate", "City Center", 12.0, 40, "Express");

        // Add routes
        transportService.addRoute(route1);
        transportService.addRoute(route2);

        // Assign vehicles to routes
        route1.assignVehicle(bus1.getVehicleId());
        route2.assignVehicle(van1.getVehicleId());
        route2.assignVehicle(van2.getVehicleId());
    }

    private void demonstrateOOPPrinciples() {
        System.out.println("\n DEMONSTRATING OOP PRINCIPLES...\n");

        // Example: Student requests transport
        String studentRequest = transportService.processTransportRequest("U001", "Library", "09:00 AM");
        System.out.println(studentRequest);

        // Example: Lecturer requests transport
        String lecturerRequest = transportService.processTransportRequest("U003", "City Center", "10:00 AM");
        System.out.println(lecturerRequest);

        // Example: Officer assigns driver
        String driverAssignment = transportService.assignDriver("B001", "John Doe", "UBG123X");
        System.out.println(driverAssignment);

        // Example: Schedule transport
        String scheduleResult = transportService.scheduleTransport("B001", "R001", new Date(), new Date(System.currentTimeMillis() + 3600000));
        System.out.println(scheduleResult);

        // Example: Emergency transport
        String emergencyResult = transportService.handleEmergencyTransport("U002", "Hospital", "Medical");
        System.out.println(emergencyResult);

        // Example: Service statistics
        String stats = transportService.getServiceStatistics();
        System.out.println(stats);

        // Example: List available vehicles
        System.out.println("Available Vehicles:");
        for (String vehicle : transportService.getAvailableVehicles()) {
            System.out.println(vehicle);
        }
    }

    private void runInteractiveMenu() {
        while (true) {
            System.out.println("\n========= MAIN MENU =========");
            System.out.println("1. Request Transport");
            System.out.println("2. List Available Vehicles");
            System.out.println("3. View Service Statistics");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            String input = scanner.nextLine();
            switch (input) {
                case "1" -> {
                    System.out.print("Enter User ID: ");
                    String userId = scanner.nextLine();
                    System.out.print("Enter Destination: ");
                    String destination = scanner.nextLine();
                    System.out.print("Enter Time (e.g., 09:00 AM): ");
                    String time = scanner.nextLine();
                    String result = transportService.processTransportRequest(userId, destination, time);
                    System.out.println(result);
                }
                case "2" -> {
                    System.out.println("Available Vehicles:");
                    for (String vehicle : transportService.getAvailableVehicles()) {
                        System.out.println(vehicle);
                    }
                }
                case "3" -> System.out.println(transportService.getServiceStatistics());
                case "0" -> {
                    try (scanner) {
                        System.out.println("Exiting system. Goodbye!");
                    }
                    return;
                }

                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}