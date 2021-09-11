package streams;

import beans.Employee;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamsExamples {

  public static void main(String... args) {

    List<String> courcesList = Arrays.asList("Java", "Redis", "sql", "golang");

  // Creating List from Stream
    List<String> couList = courcesList.stream().collect(Collectors.toList());
    ArrayList<String> courseArrayList = courcesList.stream().collect(Collectors.toCollection(ArrayList::new));

  // Creating Set from Stream
    Set<String> courseSet = courcesList.stream().collect(Collectors.toSet());
    HashSet<String> courseHashSet = courcesList.stream().collect(Collectors.toCollection(HashSet::new));

  // Creating Map from Stream with Employee id as key and Employee Object as Value
    Map<String, Employee>
        // using simple way
        empMap =getEmployeeList().stream().collect(Collectors.toMap(emp -> emp.getEmpID(), emp -> emp));
        // using Method reference
        empMap =getEmployeeList().stream().collect(Collectors.toMap(Employee::getEmpID, emp -> emp));
        // using Function Identity
        empMap =getEmployeeList().stream().collect(Collectors.toMap(Employee::getEmpID, Function.identity()));

        // Creating LinkedHashMap from Stream
        // (e1, e2) -> e2 represent to take second value in case of Duplicate keys
    LinkedHashMap linkedHashMap = getEmployeeList().stream()
        .collect(Collectors.toMap(Employee::getEmpID, Function.identity(), (e1, e2) -> e2,
            LinkedHashMap::new));




  }

  private static List<Employee> getEmployeeList() {

    return Arrays.asList(Employee.getEmployee().setEmpID("1").setEmpName("abc").setEmpGrade("A"),
        Employee.getEmployee().setEmpID("2").setEmpName("def").setEmpGrade("A"),
        Employee.getEmployee().setEmpID("3").setEmpName("ghi").setEmpGrade("B"));
  }

}
