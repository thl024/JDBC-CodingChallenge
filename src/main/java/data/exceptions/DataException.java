package main.java.data.exceptions;

/**
 * Created by timothylam on 10/26/16.
 *
 * This represents an exception that produced during the Data Access stage. This acts as a wrapper
 * for SQL Exceptions and Illegal Argument Exceptions that may occur when accessing the
 * database.
 */
public class DataException extends RuntimeException {

    public DataException(String message) {
        super(message);
    }

    public DataException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataException(Throwable cause) {
        super(cause);
    }

}
