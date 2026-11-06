import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/** Manages employee registration and attendance records. */
public class AttendanceManager {
  private final List<Employee> employees;
  private final List<AttendanceRecord> attendanceRecords;

  /** Creates an empty attendance manager. */
  public AttendanceManager() {
    employees = new ArrayList<>();
    attendanceRecords = new ArrayList<>();
  }

  /**
   * Registers an employee.
   *
   * @param employee the employee to register
   * @throws EmployeeAlreadyExistsException if the ID is already registered
   */
  public void registerEmployee(Employee employee) throws EmployeeAlreadyExistsException {
    boolean alreadyRegistered = employees.stream()
        .anyMatch(existing -> existing.getEmployeeId().equals(employee.getEmployeeId()));
    if (alreadyRegistered) {
      throw new EmployeeAlreadyExistsException(
          "Employee ID " + employee.getEmployeeId() + " is already registered.");
    }
    employees.add(employee);
  }

  /**
   * Finds an employee by ID.
   *
   * @param employeeId the ID to search for
   * @return the matching employee
   * @throws EmployeeNotFoundException if the employee does not exist
   */
  public Employee findEmployeeById(String employeeId) throws EmployeeNotFoundException {
    return employees.stream()
        .filter(employee -> employee.getEmployeeId().equals(employeeId))
        .findFirst()
        .orElseThrow(() -> new EmployeeNotFoundException(
            "Employee ID " + employeeId + " was not found."));
  }

  /**
   * Records an employee's time-in.
   *
   * @param employeeId the employee ID
   * @throws EmployeeNotFoundException if the employee does not exist
   * @throws InvalidAttendanceException if an attendance record is already open
   */
  public void timeIn(String employeeId)
      throws EmployeeNotFoundException, InvalidAttendanceException {
    findEmployeeById(employeeId);
    boolean hasOpenRecord = attendanceRecords.stream()
        .anyMatch(record -> record.getEmployeeId().equals(employeeId)
            && record.getTimeOut() == null);
    if (hasOpenRecord) {
      throw new InvalidAttendanceException(
          "Employee ID " + employeeId + " already has an active attendance record.");
    }
    attendanceRecords.add(new AttendanceRecord(employeeId, LocalDateTime.now()));
  }

  /**
   * Records an employee's time-out.
   *
   * @param employeeId the employee ID
   * @throws EmployeeNotFoundException if the employee does not exist
   * @throws InvalidAttendanceException if no attendance record is open
   */
  public void timeOut(String employeeId)
      throws EmployeeNotFoundException, InvalidAttendanceException {
    findEmployeeById(employeeId);
    AttendanceRecord openRecord = attendanceRecords.stream()
        .filter(record -> record.getEmployeeId().equals(employeeId)
            && record.getTimeOut() == null)
        .findFirst()
        .orElseThrow(() -> new InvalidAttendanceException(
            "Employee ID " + employeeId + " does not have an active attendance record."));
    openRecord.setTimeOut(LocalDateTime.now());
  }

  /**
   * Gets an employee's completed attendance records.
   *
   * @param employeeId the employee ID
   * @return the completed records
   * @throws EmployeeNotFoundException if the employee does not exist
   */
  public List<AttendanceRecord> getCompletedAttendanceRecords(String employeeId)
      throws EmployeeNotFoundException {
    findEmployeeById(employeeId);
    return attendanceRecords.stream()
        .filter(record -> record.getEmployeeId().equals(employeeId)
            && record.getTimeOut() != null)
        .collect(Collectors.toList());
  }
}
