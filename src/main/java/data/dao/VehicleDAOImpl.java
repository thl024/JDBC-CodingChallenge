package main.java.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

import main.java.data.utils.DataUtils;
import main.java.data.utils.SQLStringBuilder;
import main.java.data.exceptions.DataException;
import main.java.data.models.Vehicle;

/**
 * Created by timothylam on 10/26/16.
 *
 * This file represents the standard implementation of the Vehicle
 * Data Access Object.
 */
public class VehicleDAOImpl implements VehicleDAO {

    private DataConnectionFactory dataFactory;

    public VehicleDAOImpl(DataConnectionFactory dataFactory) {

        /* Creates a data factory to produce a connection */
        this.dataFactory = dataFactory;
        Connection connection = dataFactory.getConnection();

        /* Creates the Vehicle table if it does not exist */
        try {
            PreparedStatement statement = DataUtils.prepareGenericStatement(connection,
                    Vehicle.VEHICLE_TABLE_CREATE);
            statement.execute();
        } catch (SQLException e) {
            throw new DataException(e.getMessage());
        }
    }

    @Override
    public List<Vehicle> get(Map<String, String> queryFilters) throws DataException {

        List<Vehicle> vehicles = new ArrayList<>();
        try {
            /* Builds the query string for the SQL statement */
            String sqlString = SQLStringBuilder.getString(Vehicle.TABLE_NAME, queryFilters.keySet().toArray());

            Connection connection = dataFactory.getConnection();
            PreparedStatement preparedStatement = DataUtils.prepareStatement(connection,
                    sqlString, false, queryFilters.values().toArray());

            /* Execute SQL statement and return list of results */
            ResultSet results = preparedStatement.executeQuery();
            while (results.next()) {
                vehicles.add(Vehicle.fromResultSet(results));
            }

            connection.close();

        } catch (SQLException e) {
            throw new DataException(e.getMessage());
        }

        return vehicles;
    }

    @Override
    public void create(Vehicle vehicle) throws IllegalArgumentException, DataException {

        /* Check initial conditions. ID must be null for insertion and conditions for Vehicle
         * fields must be met.
         */
        if (vehicle.getId() != null) {
            throw new IllegalArgumentException("Vehicle has already been created; the ID is not null.");
        }

        if (!vehicle.checkInsertionConditions()) {
            throw new DataException("One or more fields does not meet criteria for insertion");
        }

        try {
            /* Builds the SQL string */
            String sqlString = SQLStringBuilder.createString(Vehicle.TABLE_NAME, Vehicle.asPropertyNames().length);

            Connection connection = dataFactory.getConnection();
            PreparedStatement preparedStatement = DataUtils.prepareStatement(connection,
                    sqlString, true, vehicle.asProperties());

            /* Execute SQL statement */
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new DataException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    vehicle.setId(generatedKeys.getLong(1));
                } else {
                    throw new DataException("Creating user failed, no generated key obtained.");
                }
            }

            connection.close();

        } catch (SQLException e) {
            throw new DataException(e);
        }

    }

    @Override
    public void update(Vehicle vehicle) throws IllegalArgumentException, DataException {
        /* Check initial conditions. ID must be null for insertion and conditions for Vehicle
         * fields must be met.
         */
        if (vehicle.getId() == null) {
            throw new IllegalArgumentException("Vehicle cannot be updated. Its ID is null.");
        }

        if (!vehicle.checkInsertionConditions()) {
            throw new DataException("One or more fields does not meet criteria for insertion");
        }

        try {
            /* Builds the SQL string */
            String sqlString = SQLStringBuilder.updateString(Vehicle.TABLE_NAME, Vehicle.asPropertyNames());

            Connection connection = dataFactory.getConnection();
            PreparedStatement preparedStatement = DataUtils.prepareStatement(connection,
                    sqlString, false, DataUtils.rotateElements(vehicle.asProperties()));

             /* Execute SQL statement */
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new DataException("Updating vehicle failed, no rows updated.");
            }

            connection.close();
        } catch (SQLException e) {
            throw new DataException(e);
        }
    }

    @Override
    public void delete(Vehicle vehicle) throws DataException {
        try {
            /* Builds the SQL string */
            String sqlString = SQLStringBuilder.deleteString(Vehicle.TABLE_NAME);

            Connection connection = dataFactory.getConnection();
            PreparedStatement preparedStatement = DataUtils.prepareStatement(connection,
                    sqlString, false, vehicle.getId());

            /* Execute SQL statement */
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new DataException("Deleting vehicle failed, no rows affected.");
            } else {
                vehicle.setId(null);
            }

            connection.close();
        } catch (SQLException e) {
            throw new DataException(e);
        }
    }
}
