package training.epam.kravchenko.tags;

public enum RailcarXMLTag {
    TRAIN("train"),
    TOTAL_FREIGHT_CAPACITY("total_freight_capacity"),
    TOTAL_PASSENGER_CAPACITY("total_passenger_capacity"),
    ID("id"),
    RAILCAR("railcar"),
    FREIGHTCAR("freightcar"),
    PASSENGERCAR("passengercar"),
    CAPACITY("capacity");

    private String value;

    RailcarXMLTag(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
