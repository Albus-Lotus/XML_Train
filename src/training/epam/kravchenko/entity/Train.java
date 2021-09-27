package training.epam.kravchenko.entity;

import java.util.ArrayList;
import java.util.List;

public class Train {
    private int totalFreightCapacity = 0;
    private int totalPassengerCapacity = 0;
    private List<Railcar> railcars;

    public Train() {
        this.railcars = new ArrayList<>();
    }

    public Train(List<Railcar> railcars) {
        this.railcars = railcars;
        calculateCapacity();
    }

    private void calculateCapacity() {
        for(Railcar railcar: railcars) {
            if (railcar instanceof FreightCar) {
                totalFreightCapacity += railcar.getCapacity();
            } else {
                totalPassengerCapacity += railcar.getCapacity();
            }
        }
    }

    public int getTotalFreightCapacity() {
        return totalFreightCapacity;
    }

    public int getTotalPassengerCapacity() {
        return totalPassengerCapacity;
    }

    public List<Railcar> getRailcars() {
        return railcars;
    }

    @Override
    public String toString() {
        return "Train{" +
                "totalFreightCapacity=" + totalFreightCapacity +
                ", totalPassengerCapacity=" + totalPassengerCapacity +
                ", railcars=" + railcars +
                '}';
    }
}
