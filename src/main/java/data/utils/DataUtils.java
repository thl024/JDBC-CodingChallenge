package main.java.data.utils;

import main.java.data.exceptions.DataException;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by timothylam on 10/28/16.
 *
 * This is the utility class for our data layer. It only contains static
 * functions that help with producing PreparedStatements and other common
 * utilities.
 */
public class DataUtils {

    /* Prepares a generic statement from given SQL String */
    public static PreparedStatement prepareGenericStatement
            (Connection connection, String sqlString) throws SQLException {
        return connection.prepareStatement(sqlString);
    }

    /* Prepares statement from given SQL String and returnGeneratedKeys boolean
     * ReturnGeneratedKeys determines whether the call to the database will return
     * the ids of the affected database objects.
     */
    public static PreparedStatement prepareStatement
            (Connection connection, String sqlString, boolean returnGeneratedKeys,
             Object... values) throws SQLException
    {
        PreparedStatement statement = connection.prepareStatement(sqlString, returnGeneratedKeys ?
                Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS);
        setValues(statement, values);
        return statement;
    }

    /* Sets '?' values in a prepared statement object */
    private static void setValues(PreparedStatement statement, Object... values)
            throws SQLException
    {
        for (int i = 0; i < values.length; i++) {
            statement.setObject(i + 1, values[i]);
        }
    }

    /* Rotates an array of objects to the left */
    public static Object[] rotateElements(Object[] objects) {
        Object[] newObjs = new Object[objects.length];
        System.arraycopy(objects, 1, newObjs, 0, objects.length - 1);
        newObjs[objects.length - 1] = objects[0];
        return newObjs;
    }

}
