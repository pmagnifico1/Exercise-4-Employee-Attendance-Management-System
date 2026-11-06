import java.time.LocalDateTime;

/** Represents an employee's attendance transaction. */
public class AttendanceRecord {
  private final String employeeId;
  private final LocalDateTime timeIn;
  private LocalDateTime timeOut;

  /**
   * Creates an attendance record.
   *
   * @param employeeId the employee ID
   * @param timeIn the time-in value
   */
  public AttendanceRecord(String employeeId, LocalDateTime timeIn) {
    this.employeeId = employeeId;
    this.timeIn = timeIn;
    this.timeOut = null;
  }

  /**
   * Gets the employee ID.
   *
   * @return the employee ID
   */
  public String getEmployeeId() {
    return employeeId;
  }

  /**
   * Gets the time-in value.
   *
   * @return the time-in value
   */
  public LocalDateTime getTimeIn() {
    return timeIn;
  }

  /**
   * Gets the time-out value.
   *
   * @return the time-out value, or {@code null} if incomplete
   */
  public LocalDateTime getTimeOut() {
    return timeOut;
  }

  /**
   * Sets the time-out value.
   *
   * @param timeOut the time-out value
   */
  public void setTimeOut(LocalDateTime timeOut) {
    this.timeOut = timeOut;
  }
}