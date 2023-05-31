package by.fpmibsu.network.util;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

public class DBUtilTest {

    @Test
    public void testGetConnection() {
        try {
            Connection connection = DBUtil.getConnection();
            assertNotNull(connection);
            assertTrue(connection.isValid(1));
            connection.close();
        } catch (SQLException e) {
            fail("An exception occurred while getting the connection: " + e.getMessage());
        }
    }
}