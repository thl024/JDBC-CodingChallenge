package main.java.data.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import main.java.data.utils.DataConnectionProperties;
import main.java.data.exceptions.DataConfigurationException;

/**
 * Created by timothylam on 10/26/16.
 *
 * This class establishes a connection to the MYSQL db. The abstract class
 * gives freedom for the connection establishment method. For example if the user
 * provides a driver in the properties file, DriverManager will be used. Otherwise
 * DataSource will be used. The class allows for flexibility of future implementation
 * of different connection methods.
 *
 * The class also determines the properties read in the properties file. Depending on
 * the prefix name passed in, a different set of properties will be read from
 * DataConnectionProperties. A username, password, driver, and url (of the database)
 * will be read. Only the url is necessary, but if a driver is provided, a username and
 * password must be provided.
 */
public abstract class DataConnectionFactory {

    private static final String URL_PROPERTY_KEY = "url";
    private static final String DRIVER_PROPERTY_KEY = "driver";
    private static final String USER_PROPERTY_KEY = "username";
    private static final String PASSWORD_PROPERTY_KEY = "password";

    public static DataConnectionFactory getInstance(String name) throws DataConfigurationException {

        if (name == null) {
            throw new DataConfigurationException("Database name is null.");
        } else if (name.isEmpty()) {
            throw new DataConfigurationException("Database name is blank");
        }

        /* Obtains properties from properties files */
        DataConnectionProperties properties = new DataConnectionProperties(name);
        String url = properties.getProperty(URL_PROPERTY_KEY, false);
        String driver = properties.getProperty(DRIVER_PROPERTY_KEY, true);
        String username = properties.getProperty(USER_PROPERTY_KEY, true);
        String password = properties.getProperty(PASSWORD_PROPERTY_KEY, true);
        DataConnectionFactory instance;

        /* Creates connection with DataSource if no driver provided. Otherwise creates a
            connection with DriverManager. */
        if (driver == null) {
            DataSource dataSource;
            try {
                dataSource = (DataSource) new InitialContext().lookup(url);
            } catch (NamingException e) {
                throw new DataConfigurationException("Failed to initialize datasource with URL: " + url);
            }
            instance = new DataSourceConnectionFactory(dataSource);
        } else {
            instance = new DriverManagerConnectionFactory(url, username, password);
        }

        return instance;

    }

    public abstract Connection getConnection() throws DataConfigurationException;

}

/* Instantiates a connection with a DriverManager */

class DriverManagerConnectionFactory extends DataConnectionFactory {

    private String url;
    private String username;
    private String password;

    DriverManagerConnectionFactory(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new DataConfigurationException(e.getMessage());
        }
    }

}

/* Instantiates a connection with a Data Source */

class DataSourceConnectionFactory extends DataConnectionFactory {

    private DataSource dataSource;

    DataSourceConnectionFactory(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new DataConfigurationException(e.getMessage());
        }
    }
}
