package vehicle;

import interfaces.Serviceable;
import interfaces.Trackable;
import interfaces.Schedulable;

import java.util.*;

/**
 * Van class implementing multiple interfaces
 * Demonstrates Interface implementation and Multiple Inheritance of Type
 */
public class Van implements Serviceable, Trackable, Schedulable {
    
    // Private attributes (Encapsulation)
    private final String vehicleId;
    private final String model;
    private final int capacity;
    private String driverName;
    private String driverLicense;
    private String vanType; // Cargo, Passenger, Mixed
    private Date lastServiceDate;
    private final List<String> serviceHistory;
    private double currentLatitude;
    private double currentLongitude;
    private boolean trackingEnabled;
    private double totalDistanceTraveled;
    private final List<String> scheduledTrips;
    private String scheduleStatus;
    private boolean isSpecialPurpose; // For VIP, emergency, etc.
    
    // Constructor
    public Van(String vehicleId, String model, int capacity, String driverName, 
               String driverLicense, String vanType, boolean isSpecialPurpose) {
        this.vehicleId = vehicleId;
        this.model = model;
        this.capacity = capacity;
        this.driverName = driverName;
        this.driverLicense = driverLicense;
        this.vanType = vanType;
        this.isSpecialPurpose = isSpecialPurpose;
        this.lastServiceDate = new Date();
        this.serviceHistory = new ArrayList<>();
        this.currentLatitude = 0.0;
        this.currentLongitude = 0.0;
        this.trackingEnabled = false;
        this.totalDistanceTraveled = 0.0;
        this.scheduledTrips = new ArrayList<>();
        this.scheduleStatus = "Available";
    }

    public Van(String id, String van, String model, int capacity, boolean inService) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    // Getter and Setter methods (Encapsulation)
    public String getVehicleId() { return vehicleId; }
    public String getModel() { return model; }
    public int getCapacity() { return capacity; }
    public String getDriverName() { return driverName; }
    public String getDriverLicense() { return driverLicense; }
    public String getVanType() { return vanType; }
    public boolean isSpecialPurpose() { return isSpecialPurpose; }
    
    public void setDriverName(String driverName) { this.driverName = driverName; }
    public void setDriverLicense(String driverLicense) { this.driverLicense = driverLicense; }
    public void setVanType(String vanType) { this.vanType = vanType; }
    public void setSpecialPurpose(boolean specialPurpose) { this.isSpecialPurpose = specialPurpose; }
    
    // Implementation of Serviceable interface
    @Override
    public void performMaintenance() {
        System.out.println("Performing maintenance on Van " + vehicleId + " (" + vanType + ")");
        lastServiceDate = new Date();
        serviceHistory.add("Maintenance performed on " + lastServiceDate);
        if (isSpecialPurpose) {
            System.out.println("Special purpose van - enhanced maintenance performed");
        }
    }
    
    @Override
    public boolean needsService() {
        long daysSinceService = getDaysSinceLastService();
        // Special purpose vans need more frequent service
        int serviceInterval = isSpecialPurpose ? 20 : 25;
        return daysSinceService > serviceInterval;
    }
    
    @Override
    public void scheduleService(Date serviceDate) {
        System.out.println("Service scheduled for Van " + vehicleId + " on " + serviceDate);
        serviceHistory.add("Service scheduled for " + serviceDate);
    }
    
    @Override
    public String getServiceHistory() {
        return String.join("; ", serviceHistory);
    }
    
    @Override
    public void updateServiceRecord(String serviceDetails) {
        serviceHistory.add(serviceDetails + " - " + new Date());
        System.out.println("Service record updated for Van " + vehicleId);
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
        return "Van " + vehicleId + " (" + vanType + ") location: (" + 
               currentLatitude + ", " + currentLongitude + ")";
    }
    
    @Override
    public void updateLocation(double latitude, double longitude) {
        if (trackingEnabled) {
            double distance = calculateDistance(this.currentLatitude, this.currentLongitude, latitude, longitude);
            totalDistanceTraveled += distance;
        }
        this.currentLatitude = latitude;
        this.currentLongitude = longitude;
        System.out.println("Van " + vehicleId + " location updated to: (" + latitude + ", " + longitude + ")");
    }
    
    @Override
    public String getLocationHistory() {
        return "Van " + vehicleId + " has traveled " + totalDistanceTraveled + " km";
    }
    
    @Override
    public double getDistanceTraveled() {
        return totalDistanceTraveled;
    }
    
    @Override
    public void startTracking() {
        trackingEnabled = true;
        System.out.println("GPS tracking started for Van " + vehicleId);
        if (isSpecialPurpose) {
            System.out.println("Enhanced tracking enabled for special purpose van");
        }
    }
    
    @Override
    public void stopTracking() {
        trackingEnabled = false;
        System.out.println("GPS tracking stopped for Van " + vehicleId);
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
        System.out.println("Trip scheduled for Van " + vehicleId + " - " + trip);
    }
    
    @Override
    public void cancelScheduledTrip(String tripId) {
        scheduledTrips.removeIf(trip -> trip.contains(tripId));
        System.out.println("Trip " + tripId + " cancelled for Van " + vehicleId);
    }
    
    @Override
    public void updateSchedule(String tripId, Date newDepartureTime, Date newArrivalTime) {
        System.out.println("Schedule updated for Van " + vehicleId + " - Trip: " + tripId);
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
        return Math.sqrt(Math.pow(lat2 - lat1, 2) + Math.pow(lon2 - lon1, 2)) * 111;
    }
    
    // Van-specific methods
    public String loadCargo(String cargoDescription, double weight) {
        if (vanType.equals("Cargo") || vanType.equals("Mixed")) {
            return "Van " + vehicleId + " loaded with cargo: " + cargoDescription + 
                   " (Weight: " + weight + " kg)";
        } else {
            return "Van " + vehicleId + " is not configured for cargo transport";
        }
    }
    
    public String configureForSpecialService(String serviceType) {
        if (isSpecialPurpose) {
            return "Van " + vehicleId + " configured for special service: " + serviceType;
        } else {
            return "Van " + vehicleId + " is not designated for special services";
        }
    }
    
    @Override
    public String toString() {
        return "Van{" +
                "vehicleId='" + vehicleId + '\'' +
                ", model='" + model + '\'' +
                ", capacity=" + capacity +
                ", vanType='" + vanType + '\'' +
                ", driver='" + driverName + '\'' +
                ", isSpecialPurpose=" + isSpecialPurpose +
                ", status='" + scheduleStatus + '\'' +
                ", maintenanceStatus='" + getMaintenanceStatus() + '\'' +
                '}';
    }
}
