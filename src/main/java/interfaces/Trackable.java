package interfaces;

/**
 * Interface defining trackable behaviors for vehicles
 * Demonstrates Interface principle and Abstraction
 */
public interface Trackable {
    
    // Abstract methods for tracking functionality
    String getCurrentLocation();
    
    void updateLocation(double latitude, double longitude);
    
    String getLocationHistory();
    
    double getDistanceTraveled();
    
    void startTracking();
    
    void stopTracking();
    
    boolean isCurrentlyTracked();
    
    String getTrackingStatus();
    
    void setTrackingEnabled(boolean enabled);
    
    // Default method for emergency tracking
    default void enableEmergencyTracking() {
        System.out.println("Emergency tracking activated");
        startTracking();
        setTrackingEnabled(true);
    }
    
    // Static method for tracking guidelines
    static String getTrackingGuidelines() {
        return """
               Vehicle Tracking Guidelines:
               1. GPS tracking must be active during operation
               2. Location updates every 5 minutes
               3. Emergency tracking available 24/7
               4. Historical data retained for 90 days""";
    }
}