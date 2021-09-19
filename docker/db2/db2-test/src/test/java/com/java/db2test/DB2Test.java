package com.java.db2test;

import org.junit.ClassRule;
import org.junit.Test;
import org.testcontainers.containers.Db2Container;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class DB2Test {

    @ClassRule
    public static Db2Container db2 = new Db2Container()
            .acceptLicense();

    @Test
    public void testJdbcUrl() {
        String url = db2.getJdbcUrl();
        assertNotNull(url);
    }

    @Test
    public void testJdbcConnection() throws SQLException {
        String jdbcUrl = db2.getJdbcUrl();
        Connection connection = DriverManager.getConnection(jdbcUrl, "db2inst1", "foobar1234");
        assertTrue(connection.isValid(2000));
    }

}
