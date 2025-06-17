package services;

import java.util.ArrayList;
import java.util.List;

/**
 * Route class for managing transport routes
 * Demonstrates Encapsulation and data management
 */
public class Route {
    
    // Private attributes (Encapsulation)
    private final String routeId;
    private String routeName;
    private final String startLocation;
    private final String endLocation;
    private final List<String> stopPoints;
    private double totalDistance;
    private int estimatedTravelTime; // in minutes
    private final List<String> assignedVehicles;
    private String routeType; // Regular, Express, Emergency
    private boolean isActive;
    private int maxCapacity;
    
    // Constructor
    public Route(String routeId, String routeName, String startLocation, 
                String endLocation, double totalDistance, int estimatedTravelTime, String routeType) {
        this.routeId = routeId;
        this.routeName = routeName;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.totalDistance = totalDistance;
        this.estimatedTravelTime = estimatedTravelTime;
        this.routeType = routeType;
        this.stopPoints = new ArrayList<>();
        this.assignedVehicles = new ArrayList<>();
        this.isActive = true;
        this.maxCapacity = 0;
    }
    
    // Getter methods (Encapsulation)
    public String getRouteId() {
        return routeId;
    }
    
    public String getRouteName() {
        return routeName;
    }
    
    public String getStartLocation() {
        return startLocation;
    }
    
    public String getEndLocation() {
        return endLocation;
    }
    
    public List<String> getStopPoints() {
        return new ArrayList<>(stopPoints);
    }
    
    public double getTotalDistance() {
        return totalDistance;
    }
    
    public int getEstimatedTravelTime() {
        return estimatedTravelTime;
    }
    
    public List<String> getAssignedVehicles() {
        return new ArrayList<>(assignedVehicles);
    }
    
    public String getRouteType() {
        return routeType;
    }
    
    public boolean isActive() {
        return isActive;
    }
    
    public int getMaxCapacity() {
        return maxCapacity;
    }
    
    // Setter methods (Encapsulation)
    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }
    
    public void setTotalDistance(double totalDistance) {
        this.totalDistance = totalDistance;
    }
    
    public void setEstimatedTravelTime(int estimatedTravelTime) {
        this.estimatedTravelTime = estimatedTravelTime;
    }
    
    public void setRouteType(String routeType) {
        this.routeType = routeType;
    }
    
    public void setActive(boolean active) {
        this.isActive = active;
    }
    
    // Route management methods
    public void addStopPoint(String stopPoint) {
        if (!stopPoints.contains(stopPoint)) {
            stopPoints.add(stopPoint);
            System.out.println("Stop point '" + stopPoint + "' added to route " + routeId);
        }
    }
    
    public void removeStopPoint(String stopPoint) {
        if (stopPoints.remove(stopPoint)) {
            System.out.println("Stop point '" + stopPoint + "' removed from route " + routeId);
        }
    }
    
    public void assignVehicle(String vehicleId) {
        if (!assignedVehicles.contains(vehicleId)) {
            assignedVehicles.add(vehicleId);
            System.out.println("Vehicle " + vehicleId + " assigned to route " + routeId);
            updateMaxCapacity();
        }
    }
    
    public void unassignVehicle(String vehicleId) {
        if (assignedVehicles.remove(vehicleId)) {
            System.out.println("Vehicle " + vehicleId + " unassigned from route " + routeId);
            updateMaxCapacity();
        }
    }
    
    private void updateMaxCapacity() {
        // Simplified capacity calculation
        maxCapacity = assignedVehicles.size() * 40; // Assuming average 40 capacity per vehicle
    }
    
    public String getRouteInfo() {
        StringBuilder info = new StringBuilder();
        info.append("Route Information:\n");
        info.append("Route ID: ").append(routeId).append("\n");
        info.append("Route Name: ").append(routeName).append("\n");
        info.append("Type: ").append(routeType).append("\n");
        info.append("Start: ").append(startLocation).append("\n");
        info.append("End: ").append(endLocation).append("\n");
        info.append("Distance: ").append(totalDistance).append(" km\n");
        info.append("Estimated Travel Time: ").append(estimatedTravelTime).append(" minutes\n");
        info.append("Status: ").append(isActive ? "Active" : "Inactive").append("\n");
        info.append("Assigned Vehicles: ").append(assignedVehicles.size()).append("\n");
        info.append("Max Capacity: ").append(maxCapacity).append(" passengers\n");
        
        if (!stopPoints.isEmpty()) {
            info.append("Stop Points: ").append(String.join(", ", stopPoints)).append("\n");
        }
        
        return info.toString();
    }
    
    public boolean hasStopPoint(String location) {
        return stopPoints.contains(location) || 
               startLocation.equals(location) || 
               endLocation.equals(location);
    }
    
    public int getStopPointCount() {
        return stopPoints.size();
    }
    
    public String getFullRoute() {
        StringBuilder route = new StringBuilder();
        route.append(startLocation);
        
        for (String stop : stopPoints) {
            route.append(" → ").append(stop);
        }
        
        route.append(" → ").append(endLocation);
        return route.toString();
    }
    
    public boolean canAccommodatePassengers(int passengerCount) {
        return passengerCount <= maxCapacity && isActive;
    }
    
    @Override
    public String toString() {
        return "Route{" +
                "routeId='" + routeId + '\'' +
                ", routeName='" + routeName + '\'' +
                ", type='" + routeType + '\'' +
                ", distance=" + totalDistance + "km" +
                ", vehicles=" + assignedVehicles.size() +
                ", active=" + isActive +
                '}';
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Route route = (Route) obj;
        return routeId.equals(route.routeId);
    }
    
    @Override
    public int hashCode() {
        return routeId.hashCode();
    }
}

