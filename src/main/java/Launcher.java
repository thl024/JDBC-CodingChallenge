package main.java;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

import main.java.data.dao.DataConnectionFactory;
import main.java.data.dao.VehicleDAOImpl;
import main.java.data.dao.VehicleDAOService;
import main.java.data.models.Vehicle;

/**
 * Created by timothylam on 10/25/16.
 *
 * This class contains the main method to run our database setup.
 */

public class Launcher {

    public static void main(String args[]) {

        /* Create data factory */
        DataConnectionFactory dataFactory = DataConnectionFactory.getInstance("mitchell_cc.dev");

        /* Create data service with actual VehicleDAO implementation */
        VehicleDAOService vehicleDAOService = new VehicleDAOService(new VehicleDAOImpl(dataFactory));

        /* Insert three vehicles into the database */
        Vehicle vehicle1 = new Vehicle.Builder()
                .make("Honda")
                .model("Civic")
                .year(1962)
                .build();
        vehicleDAOService.create(vehicle1);
        System.out.println("Vehicle successfully created: " + vehicle1);

        Vehicle vehicle2 = new Vehicle.Builder()
                .make("Lambourghini")
                .model("Huracan")
                .build();
        vehicleDAOService.create(vehicle2);
        System.out.println("Vehicle successfully created: " + vehicle2);

        Vehicle vehicle3 = new Vehicle.Builder()
                .make("Lambourghini")
                .model("Aventador")
                .build();
        vehicleDAOService.create(vehicle3);

        /* Update vehicle1 in the database */
        vehicle1.setMake("Toyota");
        vehicle1.setModel("Camry");
        vehicleDAOService.update(vehicle1);
        System.out.println("Vehicle successfully updated: " + vehicle1);

        /* Get all Lambourghini makes in the database */
        Map<String, String> filters = new HashMap<>();
        filters.put("make", "Lambourghini");
        List<Vehicle> vehicles = vehicleDAOService.get(filters);
        System.out.println("Vehicles successfully obtained: ");
        vehicles.forEach(System.out::println);

        /* Delete vehicle1 from the database */
        vehicleDAOService.delete(vehicle1);
        System.out.println("Vehicle successfully deleted: " + vehicle1);

    }
}