package AbstractFactoryChoseDB;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OracleDBTest {

    @Test
    void add() {
        OracleDB oracleDB = new OracleDB();
        String expected = "Test Added to OracleDB";
        String actual = oracleDB.add("Test ");
        assertEquals(expected, actual);
    }
}