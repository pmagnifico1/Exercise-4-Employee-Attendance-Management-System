/** Represents a registered employee. */
public class Employee {
  private final String employeeId;
  private final String name;
  private final String department;

  /**
   * Creates an employee.
   *
   * @param employeeId the employee ID
   * @param name the employee name
   * @param department the employee department
   */
  public Employee(String employeeId, String name, String department) {
    this.employeeId = employeeId;
    this.name = name;
    this.department = department;
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
   * Gets the employee name.
   *
   * @return the employee name
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the employee department.
   *
   * @return the employee department
   */
  public String getDepartment() {
    return department;
  }
}
