package main.java.data.exceptions;

/**
 * Created by timothylam on 10/26/16.
 *
 * This represents an exception produced during the Data Access Configuration stage.
 * It serves as a wrapper for exceptions that occur when configuring database access
 * properties.
 */
public class DataConfigurationException extends RuntimeException {

    public DataConfigurationException(String message) {
        super(message);
    }

    public DataConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataConfigurationException(Throwable cause) {
        super(cause);
    }

}
