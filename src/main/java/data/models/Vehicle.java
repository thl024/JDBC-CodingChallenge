package main.java.data.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Created by timothylam on 10/25/16.
 *
 * This represents a basic Vehicle model. It includes a builder and basic
 * information about its property field names and values.
 */
public class Vehicle extends BaseModel {

    /* Instance Variables */

    public static final String TABLE_NAME = "Vehicle";
    public static final String YEAR_FIELD = "year";
    public static final String MAKE_FIELD = "make";
    public static final String MODEL_FIELD = "model";
    public static final String VEHICLE_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
            + " (id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT, "
            + "make VARCHAR(60) NOT NULL CHECK (make <> ''), "
            + "model VARCHAR(60) NOT NULL CHECK (model <> ''), "
            + "year SMALLINT NULL CHECK (year BETWEEN 1950 AND 2050), "
            + "PRIMARY KEY (id));";

    private int year;
    private String make;
    private String model;

    /* Constructors */

    public Vehicle() {}

    public Vehicle(Vehicle vehicle) {
        this.id = vehicle.id;
        this.year = vehicle.year;
        this.make = vehicle.make;
        this.model = vehicle.model;
    }

    /* Builder */

    public static class Builder {

        private Long id;
        private int year;
        private String make;
        private String model;

        public Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder year(int year) {
            this.year = year;
            return this;
        }

        public Builder make(String make) {
            this.make = make;
            return this;
        }

        public Builder model(String model) {
            this.model = model;
            return this;
        }

        public Vehicle build() {
            Vehicle vehicle = new Vehicle();
            vehicle.id = id;
            vehicle.make = make;
            vehicle.model = model;
            vehicle.year = year;
            return vehicle;
        }
    }


    /* Getters/Setters */

    public int getYear() {
        return year;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    /* Overrides */

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vehicle) {
            return Objects.equals(this.id, ((Vehicle) obj).id);
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("Vehicle[id=%d,model=%s,make=%s,year=%d]",
                id, model, make, year);
    }

    @Override
    public Object[] asProperties() {
        return new Object[]{
                this.id,
                this.make,
                this.model,
                this.year
        };
    }

    /* Utility functions */

    public static Object[] asPropertyNames() {
        return new Object[]{
                "id",
                MAKE_FIELD,
                MODEL_FIELD,
                YEAR_FIELD
        };
    }

    public static Vehicle fromResultSet(ResultSet resultSet) throws SQLException {
        return new Vehicle.Builder()
                .id(resultSet.getLong("id"))
                .model(resultSet.getString(MODEL_FIELD))
                .make(resultSet.getString(MAKE_FIELD))
                .year(resultSet.getInt(YEAR_FIELD))
                .build();
    }

    public boolean checkInsertionConditions() {
        return !(make == null || make.equals("") ||
                model == null || model.equals(""))
                && !(year != 0 && (year < 1950 || year > 2050));
    }
}
