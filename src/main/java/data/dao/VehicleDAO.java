package main.java.data.dao;

import main.java.data.models.Vehicle;
import main.java.data.exceptions.DataException;

import java.util.Map;
import java.util.List;

/**
 * Created by timothylam on 10/26/16.
 *
 * This interface represents a Vehicle Data Access Object. Classes inheriting
 * this interface must include the CRUD operation methods specified. This interface
 * gives flexibility for different implementations (such as the VehicleDAOMocker).
 */
public interface VehicleDAO {

    /* Obtains a list of vehicles in the database given a map of key value pairs.
     *
     * e.g. Map<String, String> filters = new HashMap<>();
     * filters.put("make", "Honda");
     * vehicleDAO.get(filters);
     *
     * This should return all vehicles in the database with a make of Honda.
     */
    List<Vehicle> get(Map<String, String> queryFilters) throws DataException;

    /* Inserts a vehicle object into the database. */
    void create(Vehicle vehicle) throws IllegalArgumentException, DataException;

    /* Updates a vehicle object into the database. */
    void update(Vehicle vehicle) throws IllegalArgumentException, DataException;

    /* Deletes a vehicle object from the database. */
    void delete(Vehicle vehicle) throws DataException;

}
