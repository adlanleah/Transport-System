package user;

/**
 * Demonstrates Abstraction and Encapsulation principles
 */

public abstract class User {
    // Private attributes demonstrating Encapsulation
    private final String userId;
    private String name;
    private String email;
    private String phoneNumber;
    private String password;
    
    // Constructor
    public User(String userId, String name, String email, String phoneNumber, String password) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }
    
    // Getter methods (Encapsulation)
    public String getUserId() {
        return userId;
    }
    
    public String getName() {
        return name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    // Setter methods (Encapsulation)
    public void setName(String name) {
        this.name = name;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    // Password validation (keeping password private)
    public boolean validatePassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }
    
    // Abstract method to be overridden by subclasses (Polymorphism)
    public abstract String requestTransport(String destination, String time);
    
    // Abstract method for user-specific information
    public abstract String getUserInfo();
    
    // Common method for all users
    public void updateProfile(String name, String email, String phoneNumber) {
        setName(name);
        setEmail(email);
        setPhoneNumber(phoneNumber);
        System.out.println("Profile updated successfully for " + this.name);
    }
    
    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
