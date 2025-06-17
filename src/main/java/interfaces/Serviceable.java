package interfaces;

import java.util.Date;

/**
 * Interface defining serviceable behaviors for vehicles
 * Demonstrates Interface principle and Abstraction
 */
public interface Serviceable {
    
    // Abstract methods that must be implemented by classes
    void performMaintenance();
    
    boolean needsService();
    
    void scheduleService(Date serviceDate);
    
    String getServiceHistory();
    
    void updateServiceRecord(String serviceDetails);
    
    int getDaysSinceLastService();
    
    String getMaintenanceStatus();
    
    // Default method (Java 8 feature)
    default void emergencyMaintenance() {
        System.out.println("Emergency maintenance initiated for vehicle");
        performMaintenance();
    }
    
    // Static method
    static String getServiceGuidelines() {
        return """
               Vehicle Service Guidelines:
               1. Regular maintenance every 30 days
               2. Emergency service when issues detected
               3. Complete service history documentation required""";
    }
}
