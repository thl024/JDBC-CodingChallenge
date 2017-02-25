package test.java.data.utils;

import main.java.data.utils.SQLStringBuilder;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by timothylam on 10/29/16.
 *
 * Unit tests for SQLStringBuilder
 */
public class SQLStringBuilderTest {

    private static final String TEST_TABLE_NAME = "test_table";

    @Test
    public void createStringTest() {
        int numParams = 5;

        String expected = "INSERT INTO test_table VALUES (?, ?, ?, ?, ?)";
        assertEquals(expected, SQLStringBuilder.createString(TEST_TABLE_NAME, numParams));
    }

    @Test
    public void updateStringTest() {
        String[] properties = {"id", "age", "gender", "birthday", "name"};

        String expected = "UPDATE test_table SET age = ?, gender = ?, birthday = ?, name = ? WHERE id = ?";
        assertEquals(expected, SQLStringBuilder.updateString(TEST_TABLE_NAME, properties));
    }

    @Test
    public void getStringTest() {
        String[] properties = {"age", "gender"};

        String expected = "SELECT * FROM test_table WHERE age = ? AND gender = ?";
        assertEquals(expected, SQLStringBuilder.getString(TEST_TABLE_NAME, properties));
    }

    @Test
    public void deleteStringTest() {
        String expected = "DELETE FROM test_table WHERE id = ?";

        assertEquals(expected, SQLStringBuilder.deleteString(TEST_TABLE_NAME));
    }

}
