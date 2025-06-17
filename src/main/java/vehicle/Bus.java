package vehicle;

import interfaces.Serviceable;
import interfaces.Trackable;
import interfaces.Schedulable;

import java.util.*;

/**
 * Bus class implementing multiple interfaces
 * Demonstrates Interface implementation and Multiple Inheritance of Type
 */
public class Bus implements Serviceable, Trackable, Schedulable {
    
    // Private attributes (Encapsulation)
    private final String vehicleId;
    private final String model;
    private final int capacity;
    private String driverName;
    private String driverLicense;
    private Date lastServiceDate;
    private final List<String> serviceHistory;
    private double currentLatitude;
    private double currentLongitude;
    private boolean trackingEnabled;
    private double totalDistanceTraveled;
    private final List<String> scheduledTrips;
    private String scheduleStatus;
    
    // Constructor
    public Bus(String vehicleId, String model, int capacity, String driverName, String driverLicense) {
        this.vehicleId = vehicleId;
        this.model = model;
        this.capacity = capacity;
        this.driverName = driverName;
        this.driverLicense = driverLicense;
        this.lastServiceDate = new Date();
        this.serviceHistory = new ArrayList<>();
        this.currentLatitude = 0.0;
        this.currentLongitude = 0.0;
        this.trackingEnabled = false;
        this.totalDistanceTraveled = 0.0;
        this.scheduledTrips = new ArrayList<>();
        this.scheduleStatus = "Available";
    }

    public Bus(String v001, String toyota, String coaster, String ubG123X, int i, boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Bus(String id, String bus, String model, int capacity, boolean inService) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Bus(String id, String bus, String model, int capacity, boolean inService) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    // Getter and Setter methods (Encapsulation)
    public String getVehicleId() { return vehicleId; }
    public String getModel() { return model; }
    public int getCapacity() { return capacity; }
    public String getDriverName() { return driverName; }
    public String getDriverLicense() { return driverLicense; }
    
    public void setDriverName(String driverName) { this.driverName = driverName; }
    public void setDriverLicense(String driverLicense) { this.driverLicense = driverLicense; }
    
    // Implementation of Serviceable interface
    @Override
    public void performMaintenance() {
        System.out.println("Performing maintenance on Bus " + vehicleId);
        lastServiceDate = new Date();
        serviceHistory.add("Maintenance performed on " + lastServiceDate);
    }
    
    @Override
    public boolean needsService() {
        long daysSinceService = getDaysSinceLastService();
        return daysSinceService > 30; // Needs service every 30 days
    }
    
    @Override
    public void scheduleService(Date serviceDate) {
        System.out.println("Service scheduled for Bus " + vehicleId + " on " + serviceDate);
        serviceHistory.add("Service scheduled for " + serviceDate);
    }
    
    @Override
    public String getServiceHistory() {
        return String.join("; ", serviceHistory);
    }
    
    @Override
    public void updateServiceRecord(String serviceDetails) {
        serviceHistory.add(serviceDetails + " - " + new Date());
        System.out.println("Service record updated for Bus " + vehicleId);
    }
    
    @Override
    public int getDaysSinceLastService() {
        long diffInMillies = Math.abs(new Date().getTime() - lastServiceDate.getTime());
        return (int) (diffInMillies / (24 * 60 * 60 * 1000));
    }
    
    @Override
    public String getMaintenanceStatus() {
        if (needsService()) {
            return "Maintenance Required";
        } else {
            return "Good Condition";
        }
    }
    
    // Implementation of Trackable interface
    @Override
    public String getCurrentLocation() {
        return "Bus " + vehicleId + " location: (" + currentLatitude + ", " + currentLongitude + ")";
    }
    
    @Override
    public void updateLocation(double latitude, double longitude) {
        // Calculate distance traveled
        if (trackingEnabled) {
            double distance = calculateDistance(this.currentLatitude, this.currentLongitude, latitude, longitude);
            totalDistanceTraveled += distance;
        }
        this.currentLatitude = latitude;
        this.currentLongitude = longitude;
        System.out.println("Bus " + vehicleId + " location updated to: (" + latitude + ", " + longitude + ")");
    }
    
    @Override
    public String getLocationHistory() {
        return "Bus " + vehicleId + " has traveled " + totalDistanceTraveled + " km";
    }
    
    @Override
    public double getDistanceTraveled() {
        return totalDistanceTraveled;
    }
    
    @Override
    public void startTracking() {
        trackingEnabled = true;
        System.out.println("GPS tracking started for Bus " + vehicleId);
    }
    
    @Override
    public void stopTracking() {
        trackingEnabled = false;
        System.out.println("GPS tracking stopped for Bus " + vehicleId);
    }
    
    @Override
    public boolean isCurrentlyTracked() {
        return trackingEnabled;
    }
    
    @Override
    public String getTrackingStatus() {
        return trackingEnabled ? "Active" : "Inactive";
    }
    
    @Override
    public void setTrackingEnabled(boolean enabled) {
        this.trackingEnabled = enabled;
    }
    
    // Implementation of Schedulable interface
    @Override
    public void scheduleTrip(String route, Date departureTime, Date arrivalTime) {
        String trip = "Route: " + route + ", Departure: " + departureTime + ", Arrival: " + arrivalTime;
        scheduledTrips.add(trip);
        System.out.println("Trip scheduled for Bus " + vehicleId + " - " + trip);
    }
    
    @Override
    public void cancelScheduledTrip(String tripId) {
        scheduledTrips.removeIf(trip -> trip.contains(tripId));
        System.out.println("Trip " + tripId + " cancelled for Bus " + vehicleId);
    }
    
    @Override
    public void updateSchedule(String tripId, Date newDepartureTime, Date newArrivalTime) {
        System.out.println("Schedule updated for Bus " + vehicleId + " - Trip: " + tripId);
    }
    
    @Override
    public List<String> getScheduledTrips() {
        return new ArrayList<>(scheduledTrips);
    }
    
    @Override
    public boolean isAvailable(Date startTime, Date endTime) {
        return scheduleStatus.equals("Available");
    }
    
    @Override
    public String getNextScheduledTrip() {
        return scheduledTrips.isEmpty() ? "No scheduled trips" : scheduledTrips.get(0);
    }
    
    @Override
    public void setScheduleStatus(String status) {
        this.scheduleStatus = status;
    }
    
    @Override
    public String getScheduleStatus() {
        return scheduleStatus;
    }
    
    @Override
    public int getScheduledTripsCount() {
        return scheduledTrips.size();
    }
    
    // Helper method to calculate distance between two points
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        // Simple distance calculation (Haversine formula simplified)
        return Math.sqrt(Math.pow(lat2 - lat1, 2) + Math.pow(lon2 - lon1, 2)) * 111; // Rough km conversion
    }
    
    // Bus-specific methods
    public String loadPassengers(int passengerCount) {
        if (passengerCount <= capacity) {
            return "Bus " + vehicleId + " loaded with " + passengerCount + " passengers";
        } else {
            return "Bus " + vehicleId + " capacity exceeded! Maximum capacity: " + capacity;
        }
    }
    
    @Override
    public String toString() {
        return "Bus{" +
                "vehicleId='" + vehicleId + '\'' +
                ", model='" + model + '\'' +
                ", capacity=" + capacity +
                ", driver='" + driverName + '\'' +
                ", status='" + scheduleStatus + '\'' +
                ", maintenanceStatus='" + getMaintenanceStatus() + '\'' +
                '}';
    }
}
