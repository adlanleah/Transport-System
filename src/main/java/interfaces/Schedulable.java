package interfaces;

import java.util.Date;
import java.util.List;

/**
 * Interface defining Schedule behaviors for transport services
 * Demonstrates Interface principle and Abstraction
 */
public interface Schedulable {
    
    // Abstract methods for scheduling functionality
    void scheduleTrip(String route, Date departureTime, Date arrivalTime);
    
    void cancelScheduledTrip(String tripId);
    
    void updateSchedule(String tripId, Date newDepartureTime, Date newArrivalTime);
    
    List<String> getScheduledTrips();
    
    boolean isAvailable(Date startTime, Date endTime);
    
    String getNextScheduledTrip();
    
    void setScheduleStatus(String status);
    
    String getScheduleStatus();
    
    int getScheduledTripsCount();
    
    // Default method for emergency scheduling
    default void scheduleEmergencyTrip(String destination, String reason) {
        System.out.println("Emergency trip scheduled to " + destination + 
                         " - Reason: " + reason);
        // Implementation would schedule immediate trip
    }
    
    // Static method for scheduling guidelines
    static String getSchedulingGuidelines() {
        return """
               Transport Scheduling Guidelines:
               1. Minimum 30 minutes between trips
               2. Maximum 12 trips per day per vehicle
               3. Emergency trips take priority
               4. Schedule updates require 24-hour notice""";
    }
}

