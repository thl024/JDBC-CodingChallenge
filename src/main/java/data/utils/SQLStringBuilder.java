package main.java.data.utils;

/**
 * Created by timothylam on 10/29/16.
 *
 * This class serves as a utility class to build SQL strings. These
 * functions are static and can be used to build SQL strings for any CRUD
 * operation.
 */
public class SQLStringBuilder {

    public static String createString(String tableName, int length) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("INSERT INTO ").append(tableName).append(" VALUES (");
        for (int i = 0; i < length; i++) {
            if (i == length - 1) {
                stringBuilder.append("?");
            } else {
                stringBuilder.append("?, ");
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public static String deleteString(String tableName) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DELETE FROM ").append(tableName)
                .append(" WHERE id = ?");
        return stringBuilder.toString();
    }

    public static String updateString(String tableName, Object... values) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UPDATE ").append(tableName).append(" SET ");
        for (int i = 1; i < values.length; i++) {
            if (i == values.length - 1) {
                stringBuilder.append(values[i].toString()).append(" = ? ");
            } else {
                stringBuilder.append(values[i].toString()).append(" = ?, ");
            }
        }
        stringBuilder.append("WHERE id = ?");
        return stringBuilder.toString();
    }

    public static String getString(String tableName, Object... values) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM ").append(tableName).append(" WHERE ");
        for (int i = 0; i < values.length; i++) {
            if (i == values.length - 1) {
                stringBuilder.append(values[i].toString()).append(" = ?");
            } else {
                stringBuilder.append(values[i].toString()).append(" = ? AND ");
            }
        }
        return stringBuilder.toString();
    }

}
