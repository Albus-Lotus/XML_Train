package training.epam.kravchenko.entity;

import java.util.Objects;

public abstract class Railcar {
    private int capacity;
    private String id;

    public Railcar() {}

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "capacity=" + capacity +
                ", id='" + id + '\'';
    }
}
