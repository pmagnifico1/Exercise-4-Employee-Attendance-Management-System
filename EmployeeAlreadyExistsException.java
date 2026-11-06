/** Indicates that an employee ID is already registered. */
public class EmployeeAlreadyExistsException extends Exception {
  /**
   * Creates the exception.
   *
   * @param message the detail message
   */
  public EmployeeAlreadyExistsException(String message) {
    super(message);
  }
}
