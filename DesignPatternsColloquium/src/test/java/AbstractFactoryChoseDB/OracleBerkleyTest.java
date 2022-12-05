package AbstractFactoryChoseDB;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OracleBerkleyTest {

    @Test
    void add() {
        DistinctDB oracleBerkley = new OracleBerkley();
        String expected = "You Added to OracleBerkley";
        String actual = oracleBerkley.add("You ");
        assertEquals(expected, actual);
    }
}