package user;

/**
 * Demonstrates Inheritance and Polymorphism
 */

public class Student extends User {
    // Private attributes specific to Student (Encapsulation)
    private final String studentId;
    private String program;
    private int year;
    private boolean hasTransportSubscription;
    
    // Constructor
    public Student(String userId, String name, String email, String phoneNumber, 
                  String password, String studentId, String program, int year) {
        super(userId, name, email, phoneNumber, password);
        this.studentId = studentId;
        this.program = program;
        this.year = year;
        this.hasTransportSubscription = false;
    }
    
    // Getter and Setter methods (Encapsulation)
    public String getStudentId() {
        return studentId;
    }
    
    public String getProgram() {
        return program;
    }
    
    public int getYear() {
        return year;
    }
    
    public boolean hasTransportSubscription() {
        return hasTransportSubscription;
    }
    
    public void setTransportSubscription(boolean hasSubscription) {
        this.hasTransportSubscription = hasSubscription;
    }
    
    public void setProgram(String program) {
        this.program = program;
    }
    
    public void setYear(int year) {
        this.year = year;
    }
    
    // Override abstract method from User (Polymorphism)
    @Override
    public String requestTransport(String destination, String time) {
        if (hasTransportSubscription) {
            return "Student transport request: " + getName() + " (" + studentId + 
                   ") requests transport to " + destination + " at " + time + 
                   " - PRIORITY: Standard student service";
        } else {
            return "Student transport request: " + getName() + " (" + studentId + 
                   ") requests transport to " + destination + " at " + time + 
                   " - NOTE: No subscription, may require payment";
        }
    }
    
    // Override abstract method from User
    @Override
    public String getUserInfo() {
        return """
               Student Information:
               Name: """ + getName() + "\n" +
               "Student ID: " + studentId + "\n" +
               "Program: " + program + "\n" +
               "Year: " + year + "\n" +
               "Email: " + getEmail() + "\n" +
               "Transport Subscription: " + (hasTransportSubscription ? "Active" : "Inactive");
    }
    
    // Student-specific method
    public String subscribeToTransport() {
        this.hasTransportSubscription = true;
        return "Transport subscription activated for student: " + getName();
    }
    
    public String unsubscribeFromTransport() {
        this.hasTransportSubscription = false;
        return "Transport subscription deactivated for student: " + getName();
    }
    
    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", name='" + getName() + '\'' +
                ", program='" + program + '\'' +
                ", year=" + year +
                ", hasSubscription=" + hasTransportSubscription +
                '}';
    }
}

