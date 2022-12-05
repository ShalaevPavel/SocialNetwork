package AbstractFactoryChoseDB;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostgreSQLTest {

    @Test
    void add() {
        PostgreSQL postgreSQL = new PostgreSQL();
        String expected = "Note Added to PostgreSQL";
        String actual = postgreSQL.add("Note ");
        assertEquals(expected, actual);
    }
}