package streams;

import beans.Employee;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamsExamples {

  public static void main(String... args) {

    List<String> courcesList = Arrays.asList("Java", "Redis", "sql", "golang");

    // 1. Creating List from Stream
    List<String> couList = courcesList.stream().collect(Collectors.toList());
    ArrayList<String> courseArrayList =
        courcesList.stream().collect(Collectors.toCollection(ArrayList::new));

    // 2. Creating Set from Stream
    Set<String> courseSet = courcesList.stream().collect(Collectors.toSet());
    HashSet<String> courseHashSet =
        courcesList.stream().collect(Collectors.toCollection(HashSet::new));

    // 3. Creating Map from Stream with Employee id as key and Employee Object as Value
    Map<String, Employee>
        // using simple way
        empMap =
        getEmployeeList().stream().collect(Collectors.toMap(emp -> emp.getEmpID(), emp -> emp));
    // using Method reference
    empMap = getEmployeeList().stream().collect(Collectors.toMap(Employee::getEmpID, emp -> emp));
    // using Function Identity
    empMap = getEmployeeList().stream()
        .collect(Collectors.toMap(Employee::getEmpID, Function.identity()));

    // Creating LinkedHashMap from Stream
    // (e1, e2) -> e2 represent to take second value in case of Duplicate keys
    LinkedHashMap linkedHashMap = getEmployeeList().stream()
        .collect(Collectors.toMap(Employee::getEmpID, Function.identity(), (e1, e2) -> e2,
            LinkedHashMap::new));

    // 4. Create concurrentMap() from Parallel Streams
    ConcurrentMap<String, Employee> concurrenMap = getEmployeeList().parallelStream()
        .collect(Collectors.toConcurrentMap(Employee::getEmpID, Function.identity()));

    // 5. Handling duplicate Values and creating ConcurrentHashMap Object
    ConcurrentHashMap concurrentHashMap = getEmployeeList().parallelStream()
        .collect(Collectors.toConcurrentMap(Employee::getEmpID, Function.identity(), (e1, e2) -> e2,
            ConcurrentHashMap::new));

    // 6. Joining all elements in Stream with "," separated
    // result "Java,Redis,sql,golang"
    String joinedCourses = courcesList.stream().collect(Collectors.joining(","));

    // 7. SummaryStatistics()
    List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5, 6);
    IntSummaryStatistics summaryStats =
        integerList.stream().collect(Collectors.summarizingInt(Integer::intValue));
    double averageValue = summaryStats.getAverage();
    int maxValue = summaryStats.getMax();
    int min = summaryStats.getMin();

    // 8. GroupBy()
    Map<String, List<Employee>> employeeGroupByGrade =
        getEmployeeList().stream().collect(Collectors.groupingBy(Employee::getEmpGrade));
    employeeGroupByGrade.forEach(
        (k, v) -> System.out.println("Employee Grade -->" + k + "Employee Size --->" + v.size()));

    // 9. partitionsBy()
    Map<Boolean, List<Employee>> partitionResults = getEmployeeList().stream()
        .collect(Collectors.partitioningBy((emp -> emp.getEmpGrade().equalsIgnoreCase("A"))));
    partitionResults.forEach((k,v) -> System.out.println("Key ->" + k + "  Employee Size->" + v.size()));

    // Collecting Partition results in to ArrayList
    Map<Boolean, ArrayList<Employee>> empPartitionArrayList = getEmployeeList().stream()
        .collect(Collectors.partitioningBy(emp -> emp.getEmpGrade().equalsIgnoreCase("A"),
            Collectors.toCollection(ArrayList::new)));

    // 10 Counting elements in Streams
    long numberOfEmployees = getEmployeeList().stream().collect(Collectors.counting());

  }

  private static List<Employee> getEmployeeList() {

    return Arrays.asList(Employee.getEmployee().setEmpID("1").setEmpName("abc").setEmpGrade("A"),
        Employee.getEmployee().setEmpID("2").setEmpName("def").setEmpGrade("A"),
        Employee.getEmployee().setEmpID("3").setEmpName("ghi").setEmpGrade("B"),
        Employee.getEmployee().setEmpID("4").setEmpName("fsd").setEmpGrade("c"),
        Employee.getEmployee().setEmpID("5").setEmpName("vxd").setEmpGrade("D"),
        Employee.getEmployee().setEmpID("6").setEmpName("ngf").setEmpGrade("D"));
  }

}
