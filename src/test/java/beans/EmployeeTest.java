import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTest {

    @Test
    public void testBuilderPattern() {
        Employee employee = new Employee.Builder()
                .firstName("John")
                .lastName("Doe")
                .age(30)
                .build();

        assertEquals("John", employee.getFirstName());
        assertEquals("Doe", employee.getLastName());
        assertEquals(30, employee.getAge());
    }

    @Test
    public void testGetters() {
        Employee employee = new Employee("Jane", "Doe", 25);

        assertEquals("Jane", employee.getFirstName());
        assertEquals("Doe", employee.getLastName());
        assertEquals(25, employee.getAge());
    }

    @Test
    public void testSingletonPattern() {
        Employee employee1 = Employee.getInstance();
        Employee employee2 = Employee.getInstance();

        assertSame(employee1, employee2, "Both instances should be the same");
    }
}