package exceptions;

/** Indicates that an employee is not registered. */
public class EmployeeNotFoundException extends Exception {
  /**
   * Creates the exception.
   *
   * @param message the detail message
   */
  public EmployeeNotFoundException(String message) {
    super(message);
  }
}
