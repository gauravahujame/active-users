import org.junit.Test;

import java.util.HashMap;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MainTest {

    @Test
    public void testMapSorting() {
        HashMap<String, Integer> unsortedMap = new HashMap<>();
        unsortedMap.put("A", 4);
        unsortedMap.put("B", 1);
        unsortedMap.put("C", 7);

        TreeMap<String, Integer> expectedMap = new TreeMap<>();
        expectedMap.put("C", 7);
        expectedMap.put("A", 4);
        expectedMap.put("B", 1);

        TreeMap<String, Integer> sortedMap =  Main.sortMapByValues(unsortedMap);

        // assert statements
        assertEquals(sortedMap, expectedMap); //check if sorted
        assertNotNull(sortedMap); //check not null
    }

}
