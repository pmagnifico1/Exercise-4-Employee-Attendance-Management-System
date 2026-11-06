import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Demonstrates the functionality of the Employee Attendance Management System.
 */
public class Main {

  private static final DateTimeFormatter FORMATTER =
      DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

  /**
   * Main entry point of the application.
   *
   * @param args command-line arguments
   */
  public static void main(String[] args) {
    AttendanceManager manager = new AttendanceManager();

    System.out.println("\n========== Test Case 1: Register Employees ==========");
    try {
      Employee emp1 = new Employee("E001", "Riri Williams", "IT");
      Employee emp2 = new Employee("E002", "Nick Fury", "HR");
      manager.registerEmployee(emp1);
      System.out.println("Successfully registered: " + emp1.getName());
      manager.registerEmployee(emp2);
      System.out.println("Successfully registered: " + emp2.getName());
    } catch (EmployeeAlreadyExistsException e) {
      System.err.println(
          "Registration failed [" + e.getClass().getSimpleName() + "]: " + e.getMessage());
    }

    System.out.println("\n========== Test Case 2: Reject Duplicate Employee ==========");
    try {
      Employee duplicateEmp = new Employee("E001", "Thor Son of Odin", "Electrical");
      manager.registerEmployee(duplicateEmp);
    } catch (EmployeeAlreadyExistsException e) {
      System.out.println(
          "Registration declined [" + e.getClass().getSimpleName() + "]: " + e.getMessage());
    }

    System.out.println("\n========== Test Case 3: Find Employee ==========");
    try {
      Employee found = manager.findEmployeeById("E001");
      System.out.println("Employee Found!");
      System.out.println("ID: " + found.getEmployeeId());
      System.out.println("Name: " + found.getName());
      System.out.println("Department: " + found.getDepartment());
      System.out.println("Thank you for using the Employee Attendance Management System.");
    } catch (EmployeeNotFoundException e) {
      System.err.println(
          "Search failed [" + e.getClass().getSimpleName() + "]: " + e.getMessage());
    }

    System.out.println("\n========== Test Case 4: Handle Unknown Employee ==========");
    try {
      manager.findEmployeeById("E999");
    } catch (EmployeeNotFoundException e) {
      System.out.println(
          "Employee lookup unsuccessful [" + e.getClass().getSimpleName() + "]: "
              + e.getMessage());
    }

    System.out.println("\n========== Test Case 5: Record Time-In ==========");
    try {
      manager.timeIn("E001");
      System.out.println("Time-in recorded successfully for E001.");
    } catch (EmployeeNotFoundException | InvalidAttendanceException e) {
      System.err.println(
          "Time-in failed [" + e.getClass().getSimpleName() + "]: " + e.getMessage());
    }

    System.out.println("\n========== Test Case 6: Reject Duplicate Time-In ==========");
    try {
      manager.timeIn("E001");
    } catch (EmployeeNotFoundException | InvalidAttendanceException e) {
      System.out.println(
          "Time-in could not be recorded [" + e.getClass().getSimpleName() + "]: "
              + e.getMessage());
    }

    System.out.println("\n========== Test Case 7: Reject Time-Out Without Time-In ==========");
    try {
      manager.timeOut("E002");
    } catch (EmployeeNotFoundException | InvalidAttendanceException e) {
      System.out.println(
          "Time-out could not be recorded [" + e.getClass().getSimpleName() + "]: "
              + e.getMessage());
    }

    System.out.println("\n========== Test Case 8: Record Time-Out ==========");
    try {
      manager.timeOut("E001");
      System.out.println("Time-out recorded successfully for E001.");
    } catch (EmployeeNotFoundException | InvalidAttendanceException e) {
      System.err.println(
          "Time-out failed [" + e.getClass().getSimpleName() + "]: " + e.getMessage());
    }

    System.out.println("\n========== Test Case 9: Record Another Attendance ==========");
    try {
      manager.timeIn("E001");
      System.out.println("New active record created for E001.");
      manager.timeOut("E001");
      System.out.println("Second record completed for E001.");
    } catch (EmployeeNotFoundException | InvalidAttendanceException e) {
      System.err.println(
          "Time-in/out failed [" + e.getClass().getSimpleName() + "]: " + e.getMessage());
    }

    System.out.println("\n========== Test Case 10: Display Completed Records ==========");
    try {
      List<AttendanceRecord> completedRecords = manager.getCompletedAttendanceRecords("E001");
      System.out.println("Completed Records for E001:");
      for (AttendanceRecord record : completedRecords) {
        String formattedIn = record.getTimeIn().format(FORMATTER);
        String formattedOut = record.getTimeOut().format(FORMATTER);
        System.out.println(" - Time In: " + formattedIn + " | Time Out: " + formattedOut);
      }
    } catch (EmployeeNotFoundException e) {
      System.err.println(
          "Failed to fetch records [" + e.getClass().getSimpleName() + "]: " + e.getMessage());
    }
  }
}