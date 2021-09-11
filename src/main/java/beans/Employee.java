package beans;

import java.io.Serializable;

public class Employee implements Serializable {

  private String empID;
  private String empName;
  private String empGrade;

  private Employee() {

  }

  public static Employee getEmployee() {
    return new Employee();
  }

  public Employee setEmpID(String empID) {
    this.empID = empID;
    return this;
  }

  public Employee setEmpName(String empName) {
    this.empName = empName;
    return this;
  }

  public Employee setEmpGrade(String empGrade) {
    this.empGrade = empGrade;
    return this;
  }

  public String getEmpID() {
    return empID;
  }

}
