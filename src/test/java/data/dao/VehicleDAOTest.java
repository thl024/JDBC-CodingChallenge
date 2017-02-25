package test.java.data.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import main.java.data.dao.VehicleDAOMocker;
import main.java.data.dao.VehicleDAOService;
import main.java.data.exceptions.DataException;
import main.java.data.models.Vehicle;
import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

/**
 * Created by timothylam on 10/29/16.
 *
 * Unit tests for VehicleDAO with VehicleDAOMocker.
 */
public class VehicleDAOTest  {

    public VehicleDAOService setup() {
        // DataConnectionFactory factory = DataConnectionFactory.getInstance("mitchell_cc.test");
        return new VehicleDAOService(new VehicleDAOMocker());
    }

    @Test
    public void testVehicleCreate() {
        VehicleDAOService service = setup();
        Vehicle vehicle = new Vehicle.Builder()
                .make("BMW")
                .model("X3")
                .year(1967)
                .build();

        service.create(vehicle);

        Vehicle expectedVehicle = new Vehicle(vehicle);
        expectedVehicle.setId(1L);

        assertTrue(vehicle.equals(expectedVehicle));
    }

    @Test(expected=DataException.class)
    public void testVehicleCreateNullMake() {
        VehicleDAOService service = setup();
        Vehicle vehicle = new Vehicle.Builder()
                .model("X3")
                .year(1967)
                .build();

        service.create(vehicle);
    }

    @Test(expected=DataException.class)
    public void testVehicleCreateEmptyMake() {
        VehicleDAOService service = setup();
        Vehicle vehicle = new Vehicle.Builder()
                .make("")
                .model("X3")
                .year(1967)
                .build();

        service.create(vehicle);
    }

    @Test(expected=DataException.class)
    public void testVehicleCreateNullModel() {
        VehicleDAOService service = setup();
        Vehicle vehicle = new Vehicle.Builder()
                .make("BMW")
                .year(1967)
                .build();

        service.create(vehicle);
    }

    @Test(expected=DataException.class)
    public void testVehicleCreateEmptyModel() {
        VehicleDAOService service = setup();
        Vehicle vehicle = new Vehicle.Builder()
                .make("BMW")
                .model("")
                .year(1967)
                .build();

        service.create(vehicle);
    }

    @Test(expected=DataException.class)
    public void testVehicleCreateIncorrectYear() {
        VehicleDAOService service = setup();
        Vehicle vehicle = new Vehicle.Builder()
                .make("BMW")
                .model("X3")
                .year(1921)
                .build();

        service.create(vehicle);
    }

    @Test
    public void testVehicleUpdate() {
        VehicleDAOService service = setup();
        Vehicle vehicle = new Vehicle.Builder()
                .id(1L)
                .make("BMW")
                .model("X3")
                .build();

        vehicle.setModel("X5");
        Vehicle expectedVehicle = new Vehicle(vehicle);

        service.update(vehicle);

        assertTrue(vehicle.equals(expectedVehicle));
    }

    @Test(expected=DataException.class)
    public void testVehicleUpdateNullMake() {
        VehicleDAOService service = setup();
        Vehicle vehicle = new Vehicle.Builder()
                .id(1L)
                .make("BMW")
                .model("X3")
                .build();

        vehicle.setMake(null);

        service.update(vehicle);
    }

    @Test(expected=DataException.class)
    public void testVehicleUpdateEmptyMake() {
        VehicleDAOService service = setup();
        Vehicle vehicle = new Vehicle.Builder()
                .id(1L)
                .make("BMW")
                .model("X3")
                .build();

        vehicle.setMake("");

        service.update(vehicle);
    }

    @Test(expected=DataException.class)
    public void testVehicleUpdateNullModel() {
        VehicleDAOService service = setup();
        Vehicle vehicle = new Vehicle.Builder()
                .id(1L)
                .make("BMW")
                .model("X3")
                .build();

        vehicle.setModel(null);

        service.update(vehicle);
    }

    @Test(expected=DataException.class)
    public void testVehicleUpdateEmptyModel() {
        VehicleDAOService service = setup();
        Vehicle vehicle = new Vehicle.Builder()
                .id(1L)
                .make("BMW")
                .model("X3")
                .build();

        vehicle.setModel("");

        service.update(vehicle);
    }

    @Test(expected=DataException.class)
    public void testVehicleUpdateIncorrectYear() {
        VehicleDAOService service = setup();
        Vehicle vehicle = new Vehicle.Builder()
                .make("BMW")
                .model("X3")
                .year(1960)
                .build();

        vehicle.setYear(41202);

        service.update(vehicle);
    }

    @Test
    public void testVehicleDelete() {
        VehicleDAOService service = setup();
        Vehicle vehicle = new Vehicle.Builder()
                .id(1L)
                .make("BMW")
                .model("X3")
                .year(1967)
                .build();

        service.delete(vehicle);

        assertNull(vehicle.getId());
    }

    @Test(expected=DataException.class)
    public void testVehicleDeleteNotInDB() {
        VehicleDAOService service = setup();
        Vehicle vehicle = new Vehicle.Builder()
                .make("BMW")
                .model("X3")
                .year(1967)
                .build();

        service.delete(vehicle);
    }

    @Test
    public void testVehicleGet() {
        VehicleDAOService service = setup();

        Map<String, String> queryStrings = new HashMap<>();
        queryStrings.put(Vehicle.MAKE_FIELD, "Honda");

        List<Vehicle> vehicles = service.get(queryStrings);

        Vehicle[] expectedList = {
                new Vehicle.Builder().id(1L).make("Honda").model("Accord").year(2000).build(),
                new Vehicle.Builder().id(2L).make("Honda").model("Civic").year(1963).build(),
                new Vehicle.Builder().id(3L).make("Honda").model("Odyssey").year(2016).build()
        };

        assertArrayEquals(expectedList, vehicles.toArray());
    }

}
