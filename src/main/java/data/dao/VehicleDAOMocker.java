package main.java.data.dao;

import main.java.data.exceptions.DataException;
import main.java.data.models.Vehicle;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by timothylam on 10/29/16.
 *
 * This class represents a VehicleDAO Mock Object used for testing.
 * All this does is return pre-determined values and internally change
 * objects that are passed in. This is meant to mock the expected results from
 * actually doing Vehicle CRUD operations.
 */
public class VehicleDAOMocker implements VehicleDAO {

    public VehicleDAOMocker() {}

    @Override
    public List<Vehicle> get(Map<String, String> queryFilters) throws DataException {
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Vehicle.Builder().id(1L).make("Honda").model("Accord").year(2000).build());
        vehicles.add(new Vehicle.Builder().id(2L).make("Honda").model("Civic").year(1963).build());
        vehicles.add(new Vehicle.Builder().id(3L).make("Honda").model("Odyssey").year(2016).build());
        return vehicles;
    }

    @Override
    public void create(Vehicle vehicle) throws IllegalArgumentException, DataException {
        if (!vehicle.checkInsertionConditions()) {
            throw new DataException("Mock create data failed");
        }
        vehicle.setId(1L);
    }

    @Override
    public void update(Vehicle vehicle) throws IllegalArgumentException, DataException {
        if (!vehicle.checkInsertionConditions()) {
            throw new DataException("Mock update data failed");
        }
    }

    @Override
    public void delete(Vehicle vehicle) throws DataException {
        if (vehicle.getId() == null) {
            throw new DataException("Mock delete data failed");
        }
        vehicle.setId(null);
    }
}
