package main.java.data.dao;

import main.java.data.exceptions.DataException;
import main.java.data.models.Vehicle;

import java.util.List;
import java.util.Map;

/**
 * Created by timothylam on 10/29/16.
 *
 * This class serves as a class for dependency injection. This allows users
 * to dynamically change their VehicleDAO implementation on the spot, giving flexibility
 * for multiple applications.
 *
 * A VehicleDAO object must be injected into this class.
 */
public class VehicleDAOService {

    private VehicleDAO vehicleDAO;

    public VehicleDAOService(VehicleDAO vehicleDAO) {
        this.vehicleDAO = vehicleDAO;
    }

    public VehicleDAO getVehicleDAO() {
        return vehicleDAO;
    }

    public void setVehicleDAO(VehicleDAO vehicleDAO) {
        this.vehicleDAO = vehicleDAO;
    }

    public List<Vehicle> get(Map<String, String> queryFilters) throws DataException {
        return vehicleDAO.get(queryFilters);
    }

    public void create(Vehicle vehicle) throws IllegalArgumentException, DataException {
        vehicleDAO.create(vehicle);
    }

    public void update(Vehicle vehicle) throws IllegalArgumentException, DataException {
        vehicleDAO.update(vehicle);
    }

    public void delete(Vehicle vehicle) throws DataException {
        vehicleDAO.delete(vehicle);
    }
    
}
