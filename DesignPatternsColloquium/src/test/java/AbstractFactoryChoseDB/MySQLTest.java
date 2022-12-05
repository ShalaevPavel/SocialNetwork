package AbstractFactoryChoseDB;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MySQLTest {

    @Test
    void add() {
        DistinctDB db = new MySQL();
        String result = db.add("Hello ");
        assertEquals("Hello Added to MySQL", result);
    }
}