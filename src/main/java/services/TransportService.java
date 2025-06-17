package services;

import user.User;
import vehicle.Bus;
import vehicle.Van;

import java.util.*;

/**
 * TransportService class handling transport requests and driver assignments
 * Demonstrates Method Overloading and service management
 */
public class TransportService {
    
    // Private attributes (Encapsulation)
    private final Map<String, User> registeredUsers;
    private final Map<String, Bus> availableBuses;
    private final Map<String, Van> availableVans;
    private final Map<String, Route> routes;
    private final List<String> transportRequests;
    private final Map<String, String> driverAssignments;
    
    // Constructor
    public TransportService() {
        this.registeredUsers = new HashMap<>();
        this.availableBuses = new HashMap<>();
        this.availableVans = new HashMap<>();
        this.routes = new HashMap<>();
        this.transportRequests = new ArrayList<>();
        this.driverAssignments = new HashMap<>();
    }
    
    // Method Overloading - Different ways to assign drivers
    
    // 1. Assign driver by vehicle type only
    public String assignDriver(String vehicleType) {
        String assignment = "Driver assigned to " + vehicleType + " - Auto-assignment based on availability";
        driverAssignments.put(vehicleType + "_" + System.currentTimeMillis(), "Auto-assigned driver");
        System.out.println("Method Overloading Demo: " + assignment);
        return assignment;
    }
    
    // 2. Assign driver by vehicle type and shift time
    public String assignDriver(String vehicleType, String shiftTime) {
        String assignment = "Driver assigned to " + vehicleType + " for " + shiftTime + " shift";
        String key = vehicleType + "_" + shiftTime;
        driverAssignments.put(key, "Driver for " + shiftTime + " shift");
        System.out.println("Method Overloading Demo: " + assignment);
        return assignment;
    }
    
    // 3. Assign specific driver to specific vehicle
    public String assignDriver(String vehicleId, String driverName, String driverLicense) {
        String assignment = "Driver " + driverName + " (License: " + driverLicense + 
                           ") assigned to vehicle " + vehicleId;
        driverAssignments.put(vehicleId, driverName);
        System.out.println("Method Overloading Demo: " + assignment);
        return assignment;
    }
    
    // 4. Assign driver with priority and special requirements
    public String assignDriver(String vehicleType, String shiftTime, String priority, String[] specialRequirements) {
        String assignment = "Priority " + priority + " driver assigned to " + vehicleType + 
                           " for " + shiftTime + " shift with special requirements: " + 
                           Arrays.toString(specialRequirements);
        String key = vehicleType + "_" + shiftTime + "_" + priority;
        driverAssignments.put(key, "Specialized driver");
        System.out.println("Method Overloading Demo: " + assignment);
        return assignment;
    }
    
    // 5. Emergency driver assignment
    public String assignDriver(String vehicleId, String emergencyType, boolean isUrgent) {
        String assignment = (isUrgent ? "URGENT " : "") + "Emergency driver assigned to vehicle " + 
                           vehicleId + " for " + emergencyType;
        driverAssignments.put(vehicleId + "_emergency", "Emergency driver");
        System.out.println("Method Overloading Demo: " + assignment);
        return assignment;
    }
    
    // Vehicle management methods
    public void addBus(Bus bus) {
        availableBuses.put(bus.getVehicleId(), bus);
        System.out.println("Bus " + bus.getVehicleId() + " added to transport service");
    }
    
    public void addVan(Van van) {
        availableVans.put(van.getVehicleId(), van);
        System.out.println("Van " + van.getVehicleId() + " added to transport service");
    }
    
    public void addRoute(Route route) {
        routes.put(route.getRouteId(), route);
        System.out.println("Route " + route.getRouteId() + " added to transport service");
    }
    
    public void registerUser(User user) {
        registeredUsers.put(user.getUserId(), user);
        System.out.println("User " + user.getName() + " registered in transport service");
    }
    
    // Transport request processing
    public String processTransportRequest(String userId, String destination, String time) {
        User user = registeredUsers.get(userId);
        if (user != null) {
            String request = user.requestTransport(destination, time);
            transportRequests.add(request);
            return "Transport request processed: " + request;
        }
        return "User not found: " + userId;
    }
    
    // Find best vehicle for request
    public String findBestVehicle(String routeId, int passengerCount) {
        Route route = routes.get(routeId);
        if (route == null) {
            return "Route not found: " + routeId;
        }
        
        // Check buses first
        for (Bus bus : availableBuses.values()) {
            if (bus.getCapacity() >= passengerCount && bus.getScheduleStatus().equals("Available")) {
                return "Bus " + bus.getVehicleId() + " recommended (Capacity: " + bus.getCapacity() + ")";
            }
        }
        
        // Check vans
        for (Van van : availableVans.values()) {
            if (van.getCapacity() >= passengerCount && van.getScheduleStatus().equals("Available")) {
                return "Van " + van.getVehicleId() + " recommended (Capacity: " + van.getCapacity() + ")";
            }
        }
        
        return "No suitable vehicle available for " + passengerCount + " passengers";
    }
    
    // Schedule management
    public String scheduleTransport(String vehicleId, String routeId, Date departureTime, Date arrivalTime) {
        // Check if it's a bus
        if (availableBuses.containsKey(vehicleId)) {
            Bus bus = availableBuses.get(vehicleId);
            Route route = routes.get(routeId);
            if (route != null) {
                bus.scheduleTrip(route.getRouteName(), departureTime, arrivalTime);
                return "Transport scheduled: Bus " + vehicleId + " on route " + routeId;
            }
        }
        
        // Check if it's a van
        if (availableVans.containsKey(vehicleId)) {
            Van van = availableVans.get(vehicleId);
            Route route = routes.get(routeId);
            if (route != null) {
                van.scheduleTrip(route.getRouteName(), departureTime, arrivalTime);
                return "Transport scheduled: Van " + vehicleId + " on route " + routeId;
            }
        }
        
        return "Vehicle or route not found";
    }
    
    // Service statistics
    public String getServiceStatistics() {
        StringBuilder stats = new StringBuilder();
        stats.append("Transport Service Statistics:\n");
        stats.append("Registered Users: ").append(registeredUsers.size()).append("\n");
        stats.append("Available Buses: ").append(availableBuses.size()).append("\n");
        stats.append("Available Vans: ").append(availableVans.size()).append("\n");
        stats.append("Active Routes: ").append(routes.size()).append("\n");
        stats.append("Transport Requests: ").append(transportRequests.size()).append("\n");
        stats.append("Driver Assignments: ").append(driverAssignments.size()).append("\n");
        
        return stats.toString();
    }
    
    // Get all available vehicles
    public List<String> getAvailableVehicles() {
        List<String> vehicles = new ArrayList<>();
        
        for (Bus bus : availableBuses.values()) {
            if (bus.getScheduleStatus().equals("Available")) {
                vehicles.add("Bus: " + bus.getVehicleId() + " (Capacity: " + bus.getCapacity() + ")");
            }
        }
        
        for (Van van : availableVans.values()) {
            if (van.getScheduleStatus().equals("Available")) {
                vehicles.add("Van: " + van.getVehicleId() + " (Capacity: " + van.getCapacity() + ")");
            }
        }
        
        return vehicles;
    }
    
    // Get driver assignments
    public Map<String, String> getDriverAssignments() {
        return new HashMap<>(driverAssignments);
    }
    
    // Get transport requests
    public List<String> getTransportRequests() {
        return new ArrayList<>(transportRequests);
    }
    
    // Emergency transport handling
    public String handleEmergencyTransport(String userId, String destination, String emergencyType) {
        User user = registeredUsers.get(userId);
        if (user != null) {
            // Find nearest available vehicle
            String vehicleId = findEmergencyVehicle();
            if (vehicleId != null) {
                String assignment = assignDriver(vehicleId, emergencyType, true);
                return "Emergency transport arranged: " + user.getName() + " to " + destination + 
                       " using " + vehicleId + ". " + assignment;
            }
        }
        return "Emergency transport could not be arranged";
    }
    
    private String findEmergencyVehicle() {
        // Priority: Special purpose vans, then any available vehicle
        for (Van van : availableVans.values()) {
            if (van.isSpecialPurpose() && van.getScheduleStatus().equals("Available")) {
                return van.getVehicleId();
            }
        }
        
        for (Bus bus : availableBuses.values()) {
            if (bus.getScheduleStatus().equals("Available")) {
                return bus.getVehicleId();
            }
        }
        
        return null;
    }
}