package main.java.data.models;

import java.io.Serializable;

/**
 * Created by timothylam on 10/26/16.
 *
 * This class represents the BaseModel that all models must extend from.
 * Basic properties of a model include an ID amd a hash. Also models must
 * have a method of comparison, a string representation, and a list of its
 * property values.
 */
public abstract class BaseModel implements Serializable {

    public Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return (id != null)
                ? (this.getClass().hashCode() + id.hashCode())
                : super.hashCode();
    }

    @Override
    public abstract boolean equals(Object obj);

    @Override
    public abstract String toString();

    public abstract Object[] asProperties();

}
