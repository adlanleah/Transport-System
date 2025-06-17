package main;

import user*;
import vehicle.*;
import services*;
import interfaces.*;

import java.util.Date;
import java.util.Scanner;

/**
 * Main TransportManagementSystem class
 * Demonstrates integration of all OOP principles and system functionality
 */
public class TransportManagementSystem {
    
    private TransportService transportService;
    private Scanner scanner;
    
    public TransportManagementSystem() {
        this.transportService = new TransportService();
        this.scanner = new Scanner(System.in);
    }
    
    public static void main(String[] args) {
        TransportManagementSystem utms = new TransportManagementSystem();
        utms.runSystem();
    }
    
    public void runSystem() {
        System.out.println("=================================================");
        System.out.println("VICTORIA UNIVERSITY TRANSPORT MANAGEMENT SYSTEM");
        System.out.println("=================================================");
        System.out.println();
        
        // Initialize system with sample data
        initializeSystemData();
        
        // Demonstrate OOP principles
        demonstrateOOPPrinciples();
        
        // Interactive menu (optional)
        // runInteractiveMenu();
    }
    
    private void initializeSystemData() {
        System.out.println("🔧 INITIALIZING SYSTEM DATA...\n");
        
        // Create users demonstrating Inheritance and Polymorphism
        Student student1 = new Student("U001", "Alice Johnson", "alice@vu.edu", "0756123456", 
                                      "pass123", "S2023001", "Computer Science", 2);
        Student student2 = new Student("U002", "Bob Smith", "bob@vu.edu", "0756123457", 
                                      "pass456", "S2023002", "Engineering", 1);
        
        Lecturer lecturer1 = new Lecturer("U003", "Dr. Sarah Wilson", "sarah@vu.edu", "0756123458", 
                                         "pass789", "L001", "Computer Science", "Senior Lecturer");
        
        TransportOfficer officer1 = new TransportOfficer("U004", "James Brown", "james