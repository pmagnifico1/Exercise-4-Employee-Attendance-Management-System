package exceptions;

/** Indicates an invalid attendance operation. */
public class InvalidAttendanceException extends Exception {
  /**
   * Creates the exception.
   *
   * @param message the detail message
   */
  public InvalidAttendanceException(String message) {
    super(message);
  }
}
