import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.stream.*;
import static org.junit.jupiter.api.Assertions.*;

public class StreamsExamplesTest {
    private List<String> stringList;
    private Set<String> stringSet;
    private Map<Integer, String> stringMap;

    @BeforeEach
    public void setUp() {
        // Set up sample data
        stringList = Arrays.asList("A", "B", "C", "A", "D");
        stringSet = new HashSet<>(stringList);
        stringMap = new HashMap<>();
        stringMap.put(1, "One");
        stringMap.put(2, "Two");
        stringMap.put(3, "Three");
    }

    @Test
    public void testCreationFromStream() {
        // Test List creation from stream
        List<String> listFromStream = Stream.of("A", "B", "C").collect(Collectors.toList());
        assertEquals(3, listFromStream.size());

        // Test Set creation from stream
        Set<String> setFromStream = Stream.of("A", "B", "C").collect(Collectors.toSet());
        assertEquals(3, setFromStream.size());

        // Test Map creation from stream
        Map<Integer, String> mapFromStream = Stream.of("A", "B", "C").collect(Collectors.toMap(String::length, s -> s, (s1, s2) -> s1));
        assertEquals(3, mapFromStream.size());
    }

    @Test
    public void testJoining() {
        String joined = stringList.stream().collect(Collectors.joining(", "));
        assertEquals("A, B, C, A, D", joined);
    }

    @Test
    public void testSummaryStatistics() {
        IntSummaryStatistics stats = stringList.stream().collect(Collectors.summarizingInt(String::length);
        assertEquals(1, stats.getMin());
        assertEquals(1, stats.getMax());
    }

    @Test
    public void testGrouping() {
        Map<Integer, List<String>> groupedByLength = stringList.stream().collect(Collectors.groupingBy(String::length));
        assertEquals(1, groupedByLength.size());
    }

    @Test
    public void testPartitioning() {
        Map<Boolean, List<String>> partitioned = stringList.stream().collect(Collectors.partitioningBy(s -> s.length() > 1));
        assertEquals(1, partitioned.get(true).size());
    }

    @Test
    public void testCounting() {
        long count = stringList.stream().count();
        assertEquals(5, count);
    }

    @Test
    public void testFiltering() {
        List<String> filtered = stringList.stream().filter(s -> s.startsWith("A")).collect(Collectors.toList());
        assertEquals(2, filtered.size());
    }

    @Test
    public void testMapping() {
        List<Integer> lengths = stringList.stream().map(String::length).collect(Collectors.toList());
        assertEquals(Arrays.asList(1, 1, 1, 1, 1), lengths);
    }

    @Test
    public void testDistinct() {
        List<String> distinct = stringList.stream().distinct().collect(Collectors.toList());
        assertEquals(4, distinct.size());
    }

    @Test
    public void testSorting() {
        List<String> sorted = stringList.stream().sorted().collect(Collectors.toList());
        assertEquals(Arrays.asList("A", "A", "B", "C", "D"), sorted);
    }
}