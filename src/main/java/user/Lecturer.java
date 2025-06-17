package user;

/**
 * Demonstrates Inheritance and Polymorphism
 */

public class Lecturer extends User {
    // Private attributes specific to Lecturer (Encapsulation)
    private final String employeeId;
    private String department;
    private String position;
    private boolean hasSpecialTransportPrivileges;
    
    // Constructor
    public Lecturer(String userId, String name, String email, String phoneNumber, 
                   String password, String employeeId, String department, String position) {
        super(userId, name, email, phoneNumber, password);
        this.employeeId = employeeId;
        this.department = department;
        this.position = position;
        this.hasSpecialTransportPrivileges = true; // Lecturers have privileges by default
    }
    
    // Getter and Setter methods (Encapsulation)
    public String getEmployeeId() {
        return employeeId;
    }
    
    public String getDepartment() {
        return department;
    }
    
    public String getPosition() {
        return position;
    }
    
    public boolean hasSpecialTransportPrivileges() {
        return hasSpecialTransportPrivileges;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
    
    public void setPosition(String position) {
        this.position = position;
    }
    
    public void setSpecialTransportPrivileges(boolean hasPrivileges) {
        this.hasSpecialTransportPrivileges = hasPrivileges;
    }
    
    // Override abstract method from User (Polymorphism)
    @Override
    public String requestTransport(String destination, String time) {
        return "Lecturer transport request: Prof. " + getName() + " (" + employeeId + 
               ") from " + department + " department requests transport to " + destination + 
               " at " + time + " - PRIORITY: High (Staff Priority)";
    }
    
    // Override abstract method from User
    @Override
    public String getUserInfo() {
        return """
               Lecturer Information:
               Name: """ + getName() + "\n" +
               "Employee ID: " + employeeId + "\n" +
               "Department: " + department + "\n" +
               "Position: " + position + "\n" +
               "Email: " + getEmail() + "\n" +
               "Special Transport Privileges: " + (hasSpecialTransportPrivileges ? "Yes" : "No");
    }
    
    // Lecturer-specific methods
    public String requestUrgentTransport(String destination, String reason) {
        return "URGENT TRANSPORT REQUEST: Prof. " + getName() + 
               " requires immediate transport to " + destination + 
               " - Reason: " + reason + " - Department: " + department;
    }
    
    public String scheduleRegularTransport(String route, String[] days, String time) {
        return "Regular transport scheduled for Prof. " + getName() + 
               " on route " + route + " at " + time + 
               " for days: " + String.join(", ", days);
    }
    
    @Override
    public String toString() {
        return "Lecturer{" +
                "employeeId='" + employeeId + '\'' +
                ", name='" + getName() + '\'' +
                ", department='" + department + '\'' +
                ", position='" + position + '\'' +
                ", hasPrivileges=" + hasSpecialTransportPrivileges +
                '}';
    }
}